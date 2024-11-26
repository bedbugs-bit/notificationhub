package com.swe.dev.notificationhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class NotificationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private String channel;

    @NotNull
    private Boolean enabled;

    public NotificationPreference() {}

    public NotificationPreference(User user, String channel, Boolean enabled) {
        this.user = user;
        this.channel = channel;
        this.enabled = enabled;
    }

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




}
