package com.example.Library_Management_API;

import com.example.Library_Management_API.entities.Regular;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Regular regularUser;

    @BeforeEach
    void setUp() {
        // ✅ Mock a Regular user
        regularUser = new Regular("John Doe", "john@example.com");
    }

    // ✅ Test Case 1: Ensure User Starts Without Suspension
    @Test
    void testUserNotSuspendedInitially() {
        assertEquals(0, regularUser.getLateReturns(), "User should start with 0 late returns");
    }

    // ✅ Test Case 2: Increase Late Returns and Suspend User
    @Test
    void testUserGetsSuspendedAfterLateReturns() {
        // Simulating 3 late returns
        regularUser.setLateReturns();
        regularUser.setLateReturns();
        regularUser.setLateReturns(); // Third late return should trigger suspension

        assertEquals(3, regularUser.getLateReturns(), "User should have 3 late returns");
        assertTrue(regularUser.isSuspended(), "User should be suspended");
    }


}
