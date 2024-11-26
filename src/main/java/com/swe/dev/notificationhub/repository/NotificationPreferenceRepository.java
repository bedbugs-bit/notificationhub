package com.swe.dev.notificationhub.repository;

import com.swe.dev.notificationhub.model.NotificationPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationPreferenceRepository extends JpaRepository<NotificationPreference, Long> {
    //    TODO: Add additional query methods

}
