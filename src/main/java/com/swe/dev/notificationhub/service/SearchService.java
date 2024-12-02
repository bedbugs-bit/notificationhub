package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.AuditLog;
import com.swe.dev.notificationhub.model.NotificationPreference;
import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.AuditLogRepository;
import com.swe.dev.notificationhub.repository.NotificationPreferenceRepository;
import com.swe.dev.notificationhub.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    private final UserRepository userRepository;
    private final NotificationPreferenceRepository preferenceRepository;
    private final AuditLogRepository auditLogRepository;

    public SearchService(UserRepository userRepository, NotificationPreferenceRepository preferenceRepository, AuditLogRepository auditLogRepository) {
        this.userRepository = userRepository;
        this.preferenceRepository = preferenceRepository;
        this.auditLogRepository = auditLogRepository;
    }

    // search preference by channel or state

    public List<User> searchUsers(String emailQuery) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(emailQuery.toLowerCase()))
                .collect(Collectors.toList());
    }



    // Search audit logs by operation or date range
    public List<AuditLog> searchAuditLogs(String operationQuery, String startDate, String endDate) {
        return auditLogRepository.findAll().stream()
                .filter(log -> log.getOperation().equalsIgnoreCase(operationQuery) &&
                        log.getTimestamp().isAfter(java.time.LocalDateTime.parse(startDate)) &&
                        log.getTimestamp().isBefore(java.time.LocalDateTime.parse(endDate)))
                .collect(Collectors.toList());
    }

    public List<NotificationPreference> searchPreferences(String channelQuery, Boolean enabled) {
        return preferenceRepository.findAll().stream()
                .filter(preference -> preference.getChannel().equalsIgnoreCase(channelQuery) &&
                        (enabled == null || preference.getEnabled().equals(enabled)))
                .collect(Collectors.toList());
    }


}
