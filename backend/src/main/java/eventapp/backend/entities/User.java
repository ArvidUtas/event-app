package eventapp.backend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {
    @Id
    private String id;
    private String userName;
    private String email;
    private String password;
}
