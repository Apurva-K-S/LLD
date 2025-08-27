package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.User;
import com.example.RestaurantManagementSystem.Enum.UserRole;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StaffService {

    private Map<String, User> staffMap = new ConcurrentHashMap<>();

    // Add or update staff user
    public void addOrUpdateStaff(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("User or user ID must not be null");
        }
        if (user.getUserRole() == null || !isStaffRole(user.getUserRole())) {
            throw new IllegalArgumentException("User role must be a staff role");
        }
        staffMap.put(user.getId(), user);
    }

    // Get staff user by id
    public User getStaffById(String id) {
        if (id == null) return null;
        return staffMap.get(id);
    }

    // Helper to verify if UserRole is a staff role (define your staff roles here)
    private boolean isStaffRole(UserRole role) {
        return role == UserRole.MANAGER || role == UserRole.CHEF || role == UserRole.WAITER;
        // Add other roles as needed
    }

    // Example: store schedules in a Map of userId to schedule (simplified)
    private Map<String, String> scheduleMap = new ConcurrentHashMap<>();

    public void setScheduleForStaff(String userId, String schedule) {
        scheduleMap.put(userId, schedule);
    }

    public String getScheduleForStaff(String userId) {
        return scheduleMap.get(userId);
    }

    // Performance tracking: store scores or comments
    private Map<String, String> performanceMap = new ConcurrentHashMap<>();

    public void updatePerformance(String userId, String performanceNotes) {
        performanceMap.put(userId, performanceNotes);
    }

    public String getPerformance(String userId) {
        return performanceMap.get(userId);
    }
}
