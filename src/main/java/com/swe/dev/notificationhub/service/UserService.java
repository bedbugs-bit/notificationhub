package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public User createUser(String email) {
        User user = new User(email);
        return userRepository.save(user);
    }

    // Find a user by ID
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    // Find a user by email
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
