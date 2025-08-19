package eventapp.backend.services;

import eventapp.backend.entities.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import eventapp.backend.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public Optional<AppUser> findUserByUsername(String username){
        return repo.findByUsername(username);
    }

    public ResponseEntity<String> registerUser(String username, String email, String password){

        String errormessage = "";
//Todo change to english
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()){
            errormessage = "Fyll i alla fält.";
        } else if (!isValidUsername(username)){
            errormessage = "Användarnamnet är för långt eller innehåller felaktiga karaktärer.";
        } else if (usernameIsTaken(username)){
            errormessage = "Användarnamnet är upptaget";
        } else if (emailIsRegistered(email)){
            errormessage = "Email är upptaget";
        } else if (!isValidPassword(password)) {
            errormessage = "Lösenordet måste vara mellan 8-20 tecken långt, innehålla minst en siffra, "
                    + "en liten bokstav, en stort bokstav och ett specialtecken. Mellanslag är ej tillåtna.";
        }

        if (!errormessage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errormessage);
        }
        repo.save(new AppUser(username, email, BCrypt.hashpw(password, BCrypt.gensalt())));
        return ResponseEntity.ok("User saved.");
    }

    public boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_]{1,20}$");
    }

    public boolean isValidPassword(String password){
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"€/*@#$%^&-+=()])(?=\\S+$).{8,20}$");
    }

    public boolean usernameIsTaken(String username) {
        return repo.findByUsername(username).isPresent();
    }
    public boolean emailIsRegistered(String email) {
        return repo.findByEmail(email).isPresent();
    }

    public ResponseEntity<String> login(String username, String password){
        return repo.findByUsername(username)
                .filter(user -> new BCryptPasswordEncoder().matches(password,user.getPassword()))
                .map(found -> ResponseEntity.ok().body("Login successful"))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password"));
    }
}
