package com.swe.dev.notificationhub.controller;

import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        // Manually create mocks
        userService = Mockito.mock(UserService.class);

        // Inject mocks into the controller
        userController = new UserController(userService);
    }
}
