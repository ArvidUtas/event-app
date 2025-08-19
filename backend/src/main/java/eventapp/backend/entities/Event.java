package eventapp.backend.entities;

import eventapp.backend.enums.Visibility;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;

@Document("Event")
public class Event {
    @Id
    private String id;
    private final String title;
    private final String organisedBy;
    private final String description;
    //private String image;
    private final Instant startTime;
    private final Instant endTime;
    private final ZoneId timeZone;
    private final String venue;
    private final Address address;
    //private String? coordinates;
    private final Visibility visibility;

    public Event(String title, String organisedBy, String description, Instant startTime,
                 Instant endTime, ZoneId timeZone, String venue, Address address, Visibility visibility) {
        this.title = title;
        this.organisedBy = organisedBy;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeZone = timeZone;
        this.venue = venue;
        this.address = address;
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Event{" +
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
}
