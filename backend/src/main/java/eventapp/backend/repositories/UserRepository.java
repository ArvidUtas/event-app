package eventapp.backend.repositories;

import eventapp.backend.entities.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<AppUser, String> {
    Optional<Object> findByUsername(String username);

    Optional<Object> findByEmail(String email);
}
