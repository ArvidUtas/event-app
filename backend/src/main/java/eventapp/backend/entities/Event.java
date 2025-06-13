package eventapp.backend.entities;

import eventapp.backend.enums.Visibility;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

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
}
