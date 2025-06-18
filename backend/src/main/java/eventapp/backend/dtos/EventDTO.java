package eventapp.backend.dtos;

import eventapp.backend.enums.Visibility;

import java.time.LocalDateTime;
import java.util.List;

public class EventDTO {
    private String id;
    private String title;
    private String organisedBy; //FK
    private String description;
    //private String image;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String venue;
    private List<String> address;
    //private String? coordinates;
    private Visibility visibility;

    public EventDTO(String title, String organisedBy, String description, LocalDateTime startTime,
                    LocalDateTime endTime, String venue, List<String> address, Visibility visibility) {
        this.title = title;
        this.organisedBy = organisedBy;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.address = address;
        this.visibility = visibility;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOrganisedBy() {
        return organisedBy;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getVenue() {
        return venue;
    }

    public List<String> getAddress() {
        return address;
    }

    public Visibility getVisibility() {
        return visibility;
    }
}