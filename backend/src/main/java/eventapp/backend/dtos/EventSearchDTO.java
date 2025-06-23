package eventapp.backend.dtos;

import java.time.Instant;
import java.time.ZoneId;

public class EventSearchDTO {
    private final String keyword;
    private final String organisedBy;
    private final Instant startTime;
    private final Instant endTime;
    private final ZoneId timeZone; // not in use yet / is it needed?
    private final String venue;
    private final String city; // not in use yet

    public EventSearchDTO(String keyword, String organisedBy, Instant startTime, Instant endTime,
                          ZoneId timeZone, String venue, String city) {
        this.keyword = keyword;
        this.organisedBy = organisedBy;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeZone = timeZone;
        this.venue = venue;
        this.city = city;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getOrganisedBy() {
        return organisedBy;
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

    public String getCity() {
        return city;
    }
}
