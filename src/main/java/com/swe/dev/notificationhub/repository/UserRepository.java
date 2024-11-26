package com.swe.dev.notificationhub.repository;

import com.swe.dev.notificationhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
//    TODO: Add additional query methods
}
