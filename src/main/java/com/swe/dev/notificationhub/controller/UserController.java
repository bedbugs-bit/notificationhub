package com.swe.dev.notificationhub.controller;

import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a user with a JSON body
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userRequest) {
        User user = userService.createUser(userRequest.getEmail());
        return ResponseEntity.ok(user);
    }

    // Get a user by their ID (path variable)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Get all users or filter by email (query parameter)
    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String email) {
        if (email != null) {
            Optional<User> user = userService.findUserByEmail(email);
            return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
