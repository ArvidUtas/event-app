package eventapp.backend.mappers;

import eventapp.backend.dtos.EventDTO;
import eventapp.backend.entities.Event;

public class Mapper {

    public Event eventDtoToEvent(EventDTO dto){
        return new Event(dto.getTitle(),dto.getOrganisedBy(), dto.getDescription(), dto.getStartTime(),dto.getEndTime(),
                dto.getTimeZone(), dto.getVenue(), dto.getAddress(),dto.getVisibility());
    }

    public EventDTO eventToEventDto(Event event){
        return new EventDTO(event.getId(), event.getTitle(), event.getOrganisedBy(), event.getDescription(), event.getStartTime(), event.getEndTime(),
                event.getTimeZone(), event.getVenue(), event.getAddress(),event.getVisibility());
    }
}