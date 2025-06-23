package eventapp.backend.services;

import eventapp.backend.dtos.EventDTO;
import eventapp.backend.dtos.EventSearchDTO;
import eventapp.backend.entities.Event;
import eventapp.backend.mappers.Mapper;
import eventapp.backend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class EventService {
    @Autowired
    private EventRepository repo;
    @Autowired
    private UserService userService;
    private final Mapper mapper = new Mapper();
    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity<String> addEvent(EventDTO event){
        try {
            event.setOrganisedBy(userService.findUserByUsername(event.getOrganisedBy()).getId()); //todo fix nullpointer
            repo.save(mapper.eventDtoToEvent(event));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add event. " + e.getMessage());
        }
        return ResponseEntity.ok().body("Event added");
    }

    public ResponseEntity<EventDTO> getEventByID(String eventID){
        return ResponseEntity.ok().body(mapper.eventToEventDto(repo.getEventById(eventID)));
    }

    public ResponseEntity<List<EventDTO>> searchEvents(EventSearchDTO searchDTO){
        Query query = new Query();

        if (searchDTO.getVenue() != null){
            query.addCriteria(Criteria.where("venue").is(searchDTO.getVenue()));
        }
        if (searchDTO.getKeyword() != null){
            query.addCriteria(Criteria.where("title").regex(".*" + Pattern.quote(searchDTO.getKeyword()) + ".*", "i"));
        }
        if (searchDTO.getOrganisedBy() != null){
            String userID = userService.findUserByUsername(searchDTO.getOrganisedBy()).getId(); //todo fix nullpointer
            if (userService != null) query.addCriteria(Criteria.where("organisedBy").is(userID));
        }
        if (searchDTO.getStartTime() != null && searchDTO.getEndTime() != null){
            query.addCriteria(Criteria.where("startTime").gte(searchDTO.getStartTime()).lte(searchDTO.getEndTime()));
        }

        List<EventDTO> result = mongoTemplate.find(query, Event.class).stream()
                .map(mapper::eventToEventDto)
                .toList();

        return ResponseEntity.ok().body(result);

    }
}
