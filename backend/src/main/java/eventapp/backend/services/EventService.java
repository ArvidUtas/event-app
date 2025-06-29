package eventapp.backend.services;

import eventapp.backend.dtos.EventDTO;
import eventapp.backend.mappers.Mapper;
import eventapp.backend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository repo;
    @Autowired
    private UserService userService;
    private final Mapper mapper = new Mapper();

    public ResponseEntity<String> addEvent(EventDTO event){
        try {
            event.setOrganisedBy(userService.findUserByUsername(event.getOrganisedBy()).getId());
            repo.save(mapper.eventDtoToEvent(event));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add event. " + e.getMessage());
        }
        return ResponseEntity.ok().body("Event added");
    }
}
