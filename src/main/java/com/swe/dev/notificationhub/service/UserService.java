package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email){
        User user = new User(email);
        return userRepository.save(user);
    }
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }
}
