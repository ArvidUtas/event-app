package eventapp.backend.dtos;

import eventapp.backend.entities.Address;
import eventapp.backend.enums.Visibility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class EventDTO {
    private String id;
    private final String title;
    private String organisedBy; //FK
    private final String description;
    //private String image;
    private final Instant startTime;
    private final Instant endTime;
    private final ZoneId timeZone;
    private final String venue;
    private final Address address;
    //private String? coordinates;
    private final Visibility visibility;

    public EventDTO(String title, String organisedBy, String description, LocalDateTime startTime,
                    LocalDateTime endTime, ZoneId timeZone, String venue, Address address, Visibility visibility) {
        this.title = title;
        this.organisedBy = organisedBy;
        this.description = description;
        this.startTime = startTime.atZone(timeZone).toInstant();
        this.endTime = endTime.atZone(timeZone).toInstant();
        this.timeZone = timeZone;
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

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public String getVenue() {
        return venue;
    }

    public Address getAddress() {
        return address;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setOrganisedBy(String organisedBy) {
        this.organisedBy = organisedBy;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", organisedBy='" + organisedBy + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", timeZone=" + timeZone +
                ", venue='" + venue + '\'' +
                ", address=" + address +
                ", visibility=" + visibility +
                '}';
    }
}