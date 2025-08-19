package eventapp.backend.controllers;

import eventapp.backend.dtos.EventDTO;
import eventapp.backend.dtos.EventSearchDTO;
import eventapp.backend.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private final EventService service;

    public EventController(EventService service){
        this.service = service;
    }

    @PostMapping("/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody EventDTO event){
        System.out.println(event);
        return service.addEvent(event);
    }

    @GetMapping("events/ID/{eventID}")
    public ResponseEntity<EventDTO> getEventByID(@PathVariable String eventID){
        return service.getEventByID(eventID);
    }

    @GetMapping("events/search")
    public ResponseEntity<List<EventDTO>> searchEvents(@ModelAttribute EventSearchDTO searchDTO){
        return service.searchEvents(searchDTO);
    }
}
