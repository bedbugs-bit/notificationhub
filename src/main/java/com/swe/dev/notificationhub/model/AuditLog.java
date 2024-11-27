package com.swe.dev.notificationhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(nullable = false)
    private String channel;

    @NotNull
    @Column(nullable = false)
    private Boolean enabled;

    @NotNull
    @Column(nullable = false)
    private String operation;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime timestamp;

    public AuditLog() {}

    public AuditLog(User user, String channel, Boolean enabled, String operation) {
        this.user = user;
        this.channel = channel;
        this.enabled = enabled;
        this.operation = operation;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getChannel() {
        return channel;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getOperation() {
        return operation;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
