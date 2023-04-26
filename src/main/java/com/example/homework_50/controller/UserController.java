package com.example.homework_50.controller;

import com.example.homework_50.dto.UserDto;
import com.example.homework_50.entity.User;
import com.example.homework_50.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin( allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        userService.createUser(user.getEmail(), user.getName(), user.getUsername(), user.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password must be provided");
        }
        UserDto userFromDb = userService.findByEmail(email);

        if (userFromDb == null) {
            return ResponseEntity.notFound().build();
        }

        if (!passwordEncoder.matches(password, userFromDb.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok("Authentication successful");
    }
}
