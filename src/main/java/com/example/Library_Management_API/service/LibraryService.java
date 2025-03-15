package com.example.Library_Management_API.service;

import com.example.Library_Management_API.entities.Book;
import com.example.Library_Management_API.entities.LibraryItem;
import com.example.Library_Management_API.repository.BookRepository;
import com.example.Library_Management_API.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    //private final ItemRepository itemrepo;
    private final BookRepository bookrepo;


    public LibraryService(ItemRepository itemrepo, BookRepository bookrepo) {
       // this.itemrepo = itemrepo;
        this.bookrepo = bookrepo;
    }



    public Book createNewBook(Book book) {
        return bookrepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookrepo.findAll();
    }
}
