package com.example.Library_Management_API;

import com.example.Library_Management_API.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BorrowedRecordTest {

    private User regularUser;
    private LibraryItem item;
    private Book book;
    private BorrowedRecord borrowedRecord;

    @BeforeEach
    void setUp() {
        // ✅ Mock user and item
        regularUser = new Regular("John Doe", "john@example.com");
        book = new Book(3, 123456789L, "John Writer");

        // ✅ Create BorrowedRecord
        borrowedRecord = new BorrowedRecord(regularUser, book);
    }

    // ✅ Test Case 1: Ensure BorrowedRecord is Created Correctly
    @Test
    void testBorrowedRecordCreation() {
        assertNotNull(borrowedRecord);
        assertEquals(regularUser, borrowedRecord.getUser());
        assertEquals(book, borrowedRecord.getItem());
        assertFalse(borrowedRecord.isReturned());
    }

    // ✅ Test Case 2: Verify Borrow Duration for Different Users
    @Test
    void testBorrowDurationForBook() {
        int borrowDays = borrowedRecord.getBorrowDuration(regularUser, book);
        assertEquals(14, borrowDays); // Regular user gets 14 days
    }

    // ✅ Test Case 3: Ensure Fine is Calculated Correctly for Late Returns
    @Test
    void testFineCalculationForLateReturn() {
        LocalDateTime dueDate = borrowedRecord.getReturnDate();
        LocalDateTime actualReturnDate = dueDate.plusDays(3); // 3 days late

        long overdueDays = java.time.Duration.between(dueDate, actualReturnDate).toDays();
        double fine = overdueDays * 0.5; // Book fine is $0.5/day

        borrowedRecord.setReturnDate(actualReturnDate);
        borrowedRecord.setFine(fine);

        assertEquals(1.5, borrowedRecord.getFine());
    }

    // ✅ Test Case 4: Verify Returned Flag is Set Correctly
    @Test
    void testReturnedFlag() {
        assertFalse(borrowedRecord.isReturned());
        borrowedRecord.setReturned(true);
        assertTrue(borrowedRecord.isReturned());
    }
}
