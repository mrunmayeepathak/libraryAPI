package com.example.Library_Management_API.Controller;

import com.example.Library_Management_API.entities.Book;
import com.example.Library_Management_API.entities.DVD;
import com.example.Library_Management_API.entities.Journal;
import com.example.Library_Management_API.entities.LibraryItem;
import com.example.Library_Management_API.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libservice;

    @Autowired
    public LibraryController(LibraryService libservice) {
        this.libservice = libservice;
    }

    @GetMapping("/items")
    public List<LibraryItem> getAllItems() {
        return libservice.getallItems();
    }


    @PostMapping("/addbook")
    public Book createNewBook(@RequestBody Book book) {
        return libservice.createNewBook(book);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libservice.getAllBooks();
    }

    @PostMapping("/addDVD")
    public DVD createNewDVD(@RequestBody DVD dvd)
    {
        return libservice.createNewDVD(dvd);
    }

    @GetMapping("/DVDs")
    public List<DVD> getAllDVDs()
    {
        return libservice.getAllDVDs();
    }

    @GetMapping("/Journal")
    public List<Journal> getAllJournals()
    {
        return libservice.getAllJournals();
    }
    @PostMapping("/addJournal")
    public Journal createNewJournal(Journal journal)
    {
        return libservice.createNewJournal(journal);
    }
}
