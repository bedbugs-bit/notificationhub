package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.NotificationPreference;
import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.NotificationPreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationPreferenceService {

    private final NotificationPreferenceRepository preferenceRepository;

    public NotificationPreferenceService(NotificationPreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    // Create a new preference for a user
    public NotificationPreference createPreference(User user, String channel, Boolean enabled) {
        NotificationPreference preference = new NotificationPreference(user, channel, enabled);
        return preferenceRepository.save(preference);
    }

    // Get all preferences for a user
    public List<NotificationPreference> getPreferencesByUser(User user) {
        return preferenceRepository.findAll().stream()
                .filter(preference -> preference.getUser().getId().equals(user.getId()))
                .toList();
    }

    // Get preferences by user and channel
    public List<NotificationPreference> getPreferencesByUserAndChannel(User user, String channel) {
        return preferenceRepository.findAll().stream()
                .filter(preference -> preference.getUser().getId().equals(user.getId()) &&
                        preference.getChannel().equalsIgnoreCase(channel))
                .toList();
    }
}
