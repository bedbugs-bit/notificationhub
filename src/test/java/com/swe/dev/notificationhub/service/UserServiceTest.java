package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class); // Mock the repository
        userService = new UserService(userRepository); // Inject the mock into the service
    }

    @Test
    void testCreateUser() {
        // Given
        String email = "test@example.com";
        User user = new User(email);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User createdUser = userService.createUser(email);

        // Then
        assertNotNull(createdUser);
        assertEquals(email, createdUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        // Given
        UUID id = UUID.randomUUID();
        User user = new User("test@example.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // When
        Optional<User> result = userService.getUserById(id);

        // Then
        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllUsers() {
        // Given
        List<User> users = List.of(new User("user1@example.com"), new User("user2@example.com"));
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<User> result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        assertEquals("user1@example.com", result.get(0).getEmail());
        verify(userRepository, times(1)).findAll();
    }
}
