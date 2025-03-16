package com.example.Library_Management_API;

import com.example.Library_Management_API.entities.*;
import com.example.Library_Management_API.repository.*;
import com.example.Library_Management_API.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock private ItemRepository itemrepo;
    @Mock private BookRepository bookrepo;
    @Mock private DVDrepository DVDrepo;
    @Mock private JournalRepository journalrepo;
    @Mock private UserRepository userrepo;
    @Mock private BorrowedRepository borrowedrepo;

    @InjectMocks private LibraryService libraryService;

    private User user;
    private LibraryItem book;
    private BorrowedRecord borrowRecord;

    @BeforeEach
    void setUp() {
        // ✅ Mock user
        user = new Regular("John Doe", "john@example.com");
        user.setUserId(1L);

        // ✅ Mock book item
        book = new Book(3, 123456789L, "John Writer");
        book.setItemId(5L);

        // ✅ Mock borrow record
        borrowRecord = new BorrowedRecord(user, book);
        borrowRecord.setId(100L);
        borrowRecord.setReturnDate(LocalDateTime.now().minusDays(5)); // Due 5 days ago
        borrowRecord.setReturned(false);
    }

    // ✅ Test Case 1: Creating a New User
    @Test
    void testCreateNewUser_Success() {
        when(userrepo.save(any(User.class))).thenReturn(user);

        User createdUser = libraryService.createNewUser("John Doe", "john@example.com", "Regular");

        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        assertEquals("john@example.com", createdUser.getEmail());
        assertEquals("Regular", createdUser.getUserType());

        verify(userrepo, times(1)).save(any(User.class));
    }

    // ✅ Test Case 2: Creating a New Library Item (Book)
    @Test
    void testCreateNewItem_Book_Success() {
        when(itemrepo.save(any(LibraryItem.class))).thenReturn(book);

        LibraryItem createdItem = libraryService.createNewItem("book", 3, 123456789L, null, null, "John Writer", null, null);

        assertNotNull(createdItem);
        assertEquals("Book", createdItem.getItemType());
        assertEquals(3, createdItem.getAvailableCopies());

        verify(itemrepo, times(1)).save(any(LibraryItem.class));
    }

    // ✅ Test Case 3: Borrowing a Book Successfully
    @Test
    void testBorrowItem_Success() {
        when(userrepo.findById(1L)).thenReturn(Optional.of(user));
        when(itemrepo.findById(5L)).thenReturn(Optional.of(book));
        when(borrowedrepo.save(any(BorrowedRecord.class))).thenReturn(borrowRecord);

        BorrowedRecord record = libraryService.borrowItem(1L, 5L);

        assertNotNull(record);
        assertEquals(user, record.getUser());
        assertEquals(book, record.getItem());

        verify(borrowedrepo, times(1)).save(any(BorrowedRecord.class));
        verify(itemrepo, times(1)).save(any(LibraryItem.class));
    }


    // ✅ Test Case 5: Returning a Book On Time (No Fine)
    @Test
    void testReturnItem_OnTime_NoFine() {
        borrowRecord.setReturnDate(LocalDateTime.now()); // ✅ On-time return

        when(userrepo.findById(1L)).thenReturn(Optional.of(user));
        when(itemrepo.findById(5L)).thenReturn(Optional.of(book));
        when(borrowedrepo.findByUserAndItemAndReturnedFalse(user, book))
                .thenReturn(Collections.singletonList(borrowRecord));

        LocalDateTime actualReturnDate = LocalDateTime.now();
        Map<String, Object> response = libraryService.returnItem(1L, 5L, actualReturnDate);

        assertEquals(0.0, response.get("fineAmount"));
        assertEquals(1L, response.get("userId"));
        assertEquals(5L, response.get("itemId"));

        verify(borrowedrepo).save(any(BorrowedRecord.class));
        verify(itemrepo).save(any(LibraryItem.class));
    }

    // ✅ Test Case 6: Returning a Book Late (Fine Applied)
    @Test
    void testReturnItem_Late_FineApplied() {
        LocalDateTime lateReturnDate = borrowRecord.getReturnDate().plusDays(3); // ✅ Returned 3 days late

        when(userrepo.findById(1L)).thenReturn(Optional.of(user));
        when(itemrepo.findById(5L)).thenReturn(Optional.of(book));
        when(borrowedrepo.findByUserAndItemAndReturnedFalse(user, book))
                .thenReturn(Collections.singletonList(borrowRecord));

        Map<String, Object> response = libraryService.returnItem(1L, 5L, lateReturnDate);

        assertEquals(1.5, response.get("fineAmount")); // 3 days late * $0.5 per day
        assertEquals(1L, response.get("userId"));
        assertEquals(5L, response.get("itemId"));

        verify(borrowedrepo).save(any(BorrowedRecord.class));
        verify(itemrepo).save(any(LibraryItem.class));
    }
}
