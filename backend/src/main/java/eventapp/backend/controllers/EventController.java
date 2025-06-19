package eventapp.backend.controllers;

import eventapp.backend.dtos.EventDTO;
import eventapp.backend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @Autowired
    private EventService service;

    @PostMapping("/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody EventDTO event){
        System.out.println(event);
        return service.addEvent(event);
    }
}
