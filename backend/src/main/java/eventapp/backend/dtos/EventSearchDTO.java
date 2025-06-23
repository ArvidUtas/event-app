package eventapp.backend.dtos;

import java.time.Instant;

public class EventSearchDTO {
    private final String keyword;
    private final String organisedBy;
    private final Instant startTime;
    private final Instant endTime;
    private final String venue;
    private final String city; // not in use yet

    public EventSearchDTO(String keyword, String organisedBy, Instant startTime, Instant endTime,
                          String venue, String city) {
        this.keyword = keyword;
        this.organisedBy = organisedBy;
        this.startTime = startTime;
        this.endTime = endTime;
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
