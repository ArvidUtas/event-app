package eventapp.backend.dtos;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class EventSearchDTO {
    private final String keyword;
    private final String organisedBy;
    private Instant startTime;
    private Instant endTime;
    private final String venue;
    private final String city;

    public EventSearchDTO(String keyword, String organisedBy, LocalDateTime startTime,
                          LocalDateTime endTime, ZoneId timeZone, String venue, String city) {
        this.keyword = keyword;
        this.organisedBy = organisedBy;
        this.venue = venue;
        this.city = city;
        if (startTime != null) this.startTime = startTime.atZone(timeZone).toInstant();
        if (endTime != null) this.endTime = endTime.atZone(timeZone).toInstant();
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

    public String getVenue() {
        return venue;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "EventSearchDTO{" +
                "keyword='" + keyword + '\'' +
                ", organisedBy='" + organisedBy + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", venue='" + venue + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
