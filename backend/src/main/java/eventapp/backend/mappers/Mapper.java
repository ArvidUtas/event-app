package eventapp.backend.mappers;

import eventapp.backend.dtos.EventDTO;
import eventapp.backend.entities.Event;

public class Mapper {

    public Event eventDtoToEvent(EventDTO dto){
        return new Event(dto.getTitle(),dto.getOrganisedBy(), dto.getDescription(), dto.getStartTime(),dto.getEndTime(),
                dto.getVenue(), dto.getAddress(),dto.getVisibility());
    }

}
