package com.example.Library_Management_API.service;

import com.example.Library_Management_API.entities.*;
import com.example.Library_Management_API.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibraryService {

    private final ItemRepository itemrepo;
    private final BookRepository bookrepo;
    private final DVDrepository DVDrepo;
    private final JournalRepository journalrepo;
    private final UserRepository userrepo;
    private final BorrowedRepository borrowedrepo;


    public LibraryService(ItemRepository itemrepo, BookRepository bookrepo, DVDrepository DVDrepo, JournalRepository journalRepo, UserRepository userrepo, BorrowedRepository borrowedrepo) {
        this.itemrepo = itemrepo;
        this.bookrepo = bookrepo;
        this.DVDrepo = DVDrepo;
        this.journalrepo = journalRepo;
        this.userrepo = userrepo;
        this.borrowedrepo = borrowedrepo;
    }



    /*public Book createNewBook(Book book) {
        return bookrepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookrepo.findAll();
    }



    public DVD createNewDVD(DVD dvd)
    {
        return DVDrepo.save(dvd);
    }

    public List<DVD> getAllDVDs() {
        return DVDrepo.findAll();
    }

    public List<Journal> getAllJournals() {
        return journalrepo.findAll();
    }

    public Journal createNewJournal(Journal journal)
    {
        return journalrepo.save(journal);
    }*/

    public LibraryItem createNewItem(String itemType, int availableCopies, Long isbn, Long issn, Long asin, String author, String director, String writer )
    {
        LibraryItem item;

        switch(itemType.toLowerCase())
        {
            case "book" :
                item = new Book(availableCopies, isbn, author);
                break;
            case "dvd":
                item = new DVD(availableCopies,asin, director);
                break;
            case "journal":
                item = new Journal(availableCopies,issn,writer);
                break;
            default :
                throw new IllegalArgumentException("Invalid userType: " + itemType);
        }
        return itemrepo.save(item);
    }
    public List<LibraryItem> getallItems() {
        return itemrepo.findAll();
    }

    public List<User> getAllUser() {
        return userrepo.findAll();
    }

    public User createNewUser(String name , String email, String userType ) {
        User user;

        switch(userType.toLowerCase())
        {
            case "regular" :
                user = new Regular(name, email);
                break;

            case "premium":
                user = new Premium(name,email);
                break;
            case "student":
                user = new Student(name, email);
                break;
            default :
                throw new IllegalArgumentException("Invalid userType: " + userType);
        }
        return userrepo.save(user);


    }

    @Transactional
    public BorrowedRecord borrowItem(Long userId, Long itemId) {
       // System.out.println("Checking userId: " + userId);
        //System.out.println("Checking itemId: " + itemId);

        User user = userrepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
        LibraryItem item = itemrepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item with ID " + itemId + " not found"));

        if(user.getBorrowHistory().size()>=user.getMaxBorrow())
        {
            throw new RuntimeException("Borrow limit exceeded for user: " + user.getName());

        }

        if (item.getAvailableCopies() == 0) {
            throw new RuntimeException("No available copies of item: " + item.getItemType());
        }

        //System.out.println("Borrowing Item: " + item.getItemType() + " for User: " + user.getName());

        BorrowedRecord borrow = new BorrowedRecord(user, item);
        borrowedrepo.save(borrow);


        user.getBorrowHistory().add(borrow);
        userrepo.save(user);


        item.setAvailableCopies(item.getAvailableCopies() - 1);
        itemrepo.save(item);

        return borrow;


    }

    public Map<String, Object> returnItem(Long userId, Long itemId, LocalDateTime actualreturnDate ) {

        User user = userrepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
        LibraryItem item = itemrepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item with ID " + itemId + " not found"));


        List<BorrowedRecord> borrowRecords = borrowedrepo.findByUserAndItemAndReturnedFalse(user, item);

        if (borrowRecords.isEmpty()) {
            throw new RuntimeException("No active borrow record found for userId: " + userId + " and itemId: " + itemId);
        }



        BorrowedRecord record = borrowRecords.get(0);

        LocalDateTime dueDate = record.getReturnDate();
        record.setReturned(true);
        record.setReturnDate(actualreturnDate);
       // borrowedrepo.save(record);

        double fine = 0.0;
        if (actualreturnDate.isAfter(dueDate)) {
            long overdueDays = java.time.Duration.between(dueDate.withHour(0).withMinute(0).withSecond(0).withNano(0),
                            actualreturnDate.withHour(0).withMinute(0).withSecond(0).withNano(0))
                    .toDays();
            switch (item.getItemType()) {
                case "Book": fine = overdueDays * 0.5; break;
                case "Magazine":
                case "Journal": fine = overdueDays * 1.0; break;
                case "DVD": fine =  overdueDays * 2.0; break;
                default: fine = 0.0;
            }
        }
        record.setFine(fine);
        borrowedrepo.save(record);

        item.setAvailableCopies(item.getAvailableCopies() + 1);
        itemrepo.save(item);

        if (actualreturnDate.isAfter(dueDate)) {
            user.setLateReturns();
            userrepo.save(user);
        }

        if (user.getLateReturns() > 2) {
            user.suspendforaweek();
            userrepo.save(user);
        }
        Map<String, Object> response = new HashMap<>();

        response.put("userId", userId);
        response.put("itemId", itemId);
        response.put("returnDate", actualreturnDate);
        response.put("fineAmount", fine);
        response.put("lateReturs", user.getLateReturns());

        return response;


    }
}
