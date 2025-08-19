package eventapp.backend.services;

import eventapp.backend.dtos.EventDTO;
import eventapp.backend.dtos.EventSearchDTO;
import eventapp.backend.entities.Event;
import eventapp.backend.mappers.Mapper;
import eventapp.backend.repositories.EventRepository;
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
    private final EventRepository repo;
    private final Mapper mapper = new Mapper();
    private final MongoTemplate mongoTemplate;

    public EventService(EventRepository repo, MongoTemplate mongoTemplate) {
        this.repo = repo;
        this.mongoTemplate = mongoTemplate;
    }

    public ResponseEntity<String> addEvent(EventDTO event){
        try {
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
            query.addCriteria(Criteria.where("venue").regex("^" +
                    Pattern.quote(searchDTO.getVenue()) + "$", "i"));
        }
        if (searchDTO.getKeyword() != null){
            query.addCriteria(Criteria.where("title").regex(".*" +
                    Pattern.quote(searchDTO.getKeyword()) + ".*", "i"));
        }
        if (searchDTO.getOrganisedBy() != null){
            query.addCriteria(Criteria.where("organisedBy").is(searchDTO.getOrganisedBy()));
        }
        if (searchDTO.getCity() != null){
            query.addCriteria(Criteria.where("address.city").regex("^" +
                    Pattern.quote(searchDTO.getCity()) + "$","i"));
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
