package com.example.Library_Management_API.Controller;

import com.example.Library_Management_API.entities.*;
import com.example.Library_Management_API.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libservice;

    @Autowired
    public LibraryController(LibraryService libservice) {
        this.libservice = libservice;
    }
    @PostMapping("/addItem")
    public LibraryItem createNewItem(@RequestBody Map<String, Object> requestBody)
    {
       /* System.out.println("Received User Data: Name = " + user.getName() +
                ", Email = " + user.getEmail() +
                ", userType = " + user.getUserType());*/
        String itemType = (String) requestBody.get("itemType");
        int availableCopies = (int) requestBody.get("availableCopies");

        Long isbn = requestBody.containsKey("isbn") ? ((Number) requestBody.get("isbn")).longValue() : null;
        Long issn = requestBody.containsKey("issn") ? ((Number) requestBody.get("issn")).longValue() : null;
        Long asin = requestBody.containsKey("asin") ? ((Number) requestBody.get("asin")).longValue() : null;
        String author = (String) requestBody.get("author");
        String director = (String) requestBody.get("director");
        String writer = (String) requestBody.get("writer");


        return libservice.createNewItem(itemType, availableCopies, isbn, issn, asin, author, director,writer);
    }

    @GetMapping("/items")
    public List<LibraryItem> getAllItems() {
        return libservice.getallItems();
    }


    /*@PostMapping("/addbook")
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
    }*/

    @GetMapping("/User")
    public List<User> getAllUser()
    {
        return libservice.getAllUser();
    }

    @PostMapping("/addUser")
    public User createNewUser(@RequestBody  User user)
    {
        System.out.println("Received User Data: Name = " + user.getName() +
                ", Email = " + user.getEmail() +
                ", userType = " + user.getUserType());
        return libservice.createNewUser(user.getName(), user.getEmail(), user.getUserType());
    }

    @PostMapping("/borrow")
    public BorrowedRecord borrowItem(@RequestBody Map<String, Object>requestBody)
    {
        Long userId  = requestBody.containsKey("userId") ? ((Number) requestBody.get("userId")).longValue() : null;
        Long itemId = requestBody.containsKey("itemId") ? ((Number) requestBody.get("itemId")).longValue() : null;

        return libservice.borrowItem(userId, itemId );
    }



}
