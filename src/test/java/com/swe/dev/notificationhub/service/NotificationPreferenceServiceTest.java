package com.swe.dev.notificationhub.service;

import com.swe.dev.notificationhub.model.NotificationPreference;
import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.repository.NotificationPreferenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationPreferenceServiceTest {

    private NotificationPreferenceRepository preferenceRepository;
    private AuditLogService auditLogService;
    private NotificationPreferenceService preferenceService;

    @BeforeEach
    void setUp() {
        preferenceRepository = Mockito.mock(NotificationPreferenceRepository.class);
        auditLogService = Mockito.mock(AuditLogService.class);
        preferenceService = new NotificationPreferenceService(preferenceRepository, auditLogService);
    }

    @Test
    void testCreatePreference() {
        // Given
        User user = new User("test@example.com");
        NotificationPreference preference = new NotificationPreference(user, "EMAIL", true);
        when(preferenceRepository.save(any(NotificationPreference.class))).thenReturn(preference);

        // When
        NotificationPreference result = preferenceService.createPreference(user, "EMAIL", true);

        // Then
        assertNotNull(result);
        assertEquals("EMAIL", result.getChannel());
        assertTrue(result.getEnabled());
        verify(preferenceRepository, times(1)).save(any(NotificationPreference.class));
        verify(auditLogService, times(1)).log(user, "EMAIL", true, "CREATE");
    }

    @Test
    void testGetPreferencesByUser() {
        // Given
        User user = new User("test@example.com");
        List<NotificationPreference> preferences = List.of(
                new NotificationPreference(user, "EMAIL", true),
                new NotificationPreference(user, "SMS", false)
        );
        when(preferenceRepository.findAll()).thenReturn(preferences);

        // When
        List<NotificationPreference> result = preferenceService.getPreferencesByUser(user);

        // Then
        assertEquals(2, result.size());
        assertEquals("EMAIL", result.getFirst().getChannel());
        verify(preferenceRepository, times(1)).findAll();
    }
}
