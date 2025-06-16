package eventapp.backend.entities;

import eventapp.backend.enums.Visibility;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("Event")
@Data
public class Event {
    @Id
    private String id;
    private String title;
    private String organisedBy; //FK
    private String description;
    //private String image;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> address;
    //private String? coordinates;
    private Visibility visibility;

    public Event(String title, String organisedBy, String description, LocalDateTime startTime,
                 LocalDateTime endTime, List<String> address, Visibility visibility) {
        this.title = title;
        this.organisedBy = organisedBy;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.visibility = visibility;
    }
}
