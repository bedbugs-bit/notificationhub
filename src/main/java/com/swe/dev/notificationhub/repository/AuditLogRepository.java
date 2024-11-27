package com.swe.dev.notificationhub.repository;

import com.swe.dev.notificationhub.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
