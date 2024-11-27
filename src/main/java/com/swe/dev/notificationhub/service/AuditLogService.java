package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.AuditLog;
import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    // Method to log an operation
    public void log(User user, String channel, Boolean enabled, String operation) {
        AuditLog auditLog = new AuditLog(user, channel, enabled, operation);
        auditLogRepository.save(auditLog);
    }
}
