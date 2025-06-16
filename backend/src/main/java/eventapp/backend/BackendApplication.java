package eventapp.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner addData(EventRepository eventRepository, UserRepository userRepository){
        return (args) -> {
            userRepository.save(new User("TestMan", "test@test.se", "lidfkfklmnsvls,daiz872"));
            eventRepository.save(new Event("Test event", "fk", "Kul händelse",
                    LocalDateTime.now(),
                    LocalDateTime.now(), List.of("Adressv 1","Stockholm"), Visibility.PUBLIC));
        };
    }*/
}
