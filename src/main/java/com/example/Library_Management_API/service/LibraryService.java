package com.example.Library_Management_API.service;

import com.example.Library_Management_API.entities.Book;
import com.example.Library_Management_API.entities.DVD;
import com.example.Library_Management_API.entities.Journal;
import com.example.Library_Management_API.entities.LibraryItem;
import com.example.Library_Management_API.repository.BookRepository;
import com.example.Library_Management_API.repository.DVDrepository;
import com.example.Library_Management_API.repository.ItemRepository;
import com.example.Library_Management_API.repository.JournalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final ItemRepository itemrepo;
    private final BookRepository bookrepo;
    private final DVDrepository DVDrepo;
    private final JournalRepository journalrepo;


    public LibraryService(ItemRepository itemrepo, BookRepository bookrepo, DVDrepository DVDrepo, JournalRepository journalRepo) {
        this.itemrepo = itemrepo;
        this.bookrepo = bookrepo;
        this.DVDrepo = DVDrepo;
        this.journalrepo = journalRepo;
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
}
