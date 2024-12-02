package com.swe.dev.notificationhub.controller;

import com.swe.dev.notificationhub.model.AuditLog;
import com.swe.dev.notificationhub.model.NotificationPreference;
import com.swe.dev.notificationhub.model.User;
import com.swe.dev.notificationhub.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/api/search/users")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String email) {
        return ResponseEntity.ok(searchService.searchUsers(email));
    }

    @GetMapping("/api/search/preferences")
    public ResponseEntity<List<NotificationPreference>> searchPreferences(
            @RequestParam String channel,
            @RequestParam(required = false) Boolean enabled) {
        return ResponseEntity.ok(searchService.searchPreferences(channel, enabled));
    }

    @GetMapping("/api/search/audit-logs")
    public ResponseEntity<List<AuditLog>> searchAuditLogs(
            @RequestParam String operation,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return ResponseEntity.ok(searchService.searchAuditLogs(operation, startDate, endDate));
    }
}
