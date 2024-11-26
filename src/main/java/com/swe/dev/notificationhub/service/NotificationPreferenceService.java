package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.NotificationPreference;
import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.NotificationPreferenceRepository;

public class NotificationPreferenceService {
    private final NotificationPreferenceRepository preferenceRepository;

    public NotificationPreferenceService(NotificationPreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public NotificationPreference createPreference(User user, String channel, Boolean enabled) {
        NotificationPreference preference = new NotificationPreference(user, channel, enabled);
        return preferenceRepository.save(preference);
    }

}
