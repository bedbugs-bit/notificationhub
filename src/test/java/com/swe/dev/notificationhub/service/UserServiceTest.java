package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository; // Mocked dependency
    private UserService userService; // Unit being tested

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testCreateUser() {
        // Arrange
        String email = "test@example.com";
        User user = new User(email);
        when(userRepository.save(any(User.class))).thenReturn(user); // Mock database behavior

        // Act
        User createdUser = userService.createUser(email);

        // Assert
        assertNotNull(createdUser);
        assertEquals(email, createdUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class)); // Verify repository interaction
    }

    @Test
    void testGetUserById() {
        // Arrange
        UUID id = UUID.randomUUID();
        User user = new User("test@example.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(id);
    }
}
