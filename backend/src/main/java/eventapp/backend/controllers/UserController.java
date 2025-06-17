package eventapp.backend.controllers;

import eventapp.backend.entities.AppUser;
import eventapp.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //todo change to DTO?
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AppUser user){
        return userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser user){
        return userService.login(user.getUsername(), user.getPassword());
    }
}
