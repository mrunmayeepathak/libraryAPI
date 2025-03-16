package com.example.Library_Management_API.service;

import com.example.Library_Management_API.entities.*;
import com.example.Library_Management_API.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibraryService {

    private final ItemRepository itemrepo;
    private final BookRepository bookrepo;
    private final DVDrepository DVDrepo;
    private final JournalRepository journalrepo;
    private final UserRepository userrepo;


    public LibraryService(ItemRepository itemrepo, BookRepository bookrepo, DVDrepository DVDrepo, JournalRepository journalRepo, UserRepository userrepo) {
        this.itemrepo = itemrepo;
        this.bookrepo = bookrepo;
        this.DVDrepo = DVDrepo;
        this.journalrepo = journalRepo;
        this.userrepo = userrepo;
    }



    public Book createNewBook(Book book) {
        return bookrepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookrepo.findAll();
    }

    public List<LibraryItem> getallItems() {
        return itemrepo.findAll();
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
}
