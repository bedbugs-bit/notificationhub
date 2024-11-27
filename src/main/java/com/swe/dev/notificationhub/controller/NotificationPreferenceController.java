package com.swe.dev.notificationhub.controller;

import com.swe.dev.notificationhub.model.NotificationPreference;
import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.service.NotificationPreferenceService;
import com.swe.dev.notificationhub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/preferences")
public class NotificationPreferenceController {

    private final NotificationPreferenceService preferenceService;
    private final UserService userService;

    public NotificationPreferenceController(NotificationPreferenceService preferenceService, UserService userService) {
        this.preferenceService = preferenceService;
        this.userService = userService;
    }

    // Create a preference for a user with a JSON payload
    @PostMapping("/{userId}")
    public ResponseEntity<NotificationPreference> createPreference(
            @PathVariable UUID userId,
            @RequestBody NotificationPreference preferenceRequest) {

        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        NotificationPreference preference = preferenceService.createPreference(
                user.get(),
                preferenceRequest.getChannel(),
                preferenceRequest.getEnabled()
        );
        return ResponseEntity.ok(preference);
    }

    // Get all preferences for a user by their ID (path variable)
    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationPreference>> getPreferences(@PathVariable UUID userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<NotificationPreference> preferences = preferenceService.getPreferencesByUser(user.get());
        return ResponseEntity.ok(preferences);
    }

    // Get preferences by user ID and filter by channel (query parameter)
    @GetMapping("/{userId}/filter")
    public ResponseEntity<List<NotificationPreference>> getPreferencesByChannel(
            @PathVariable UUID userId,
            @RequestParam String channel) {

        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<NotificationPreference> preferences = preferenceService.getPreferencesByUserAndChannel(user.get(), channel);
        return ResponseEntity.ok(preferences);
    }
}
