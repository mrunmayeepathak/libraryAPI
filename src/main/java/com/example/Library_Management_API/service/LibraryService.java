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

    private final ItemRepository itemrepo;
    private final BookRepository bookrepo;


    public LibraryService(ItemRepository itemrepo, BookRepository bookrepo) {
        this.itemrepo = itemrepo;
        this.bookrepo = bookrepo;
    }

public List<LibraryItem> getallItems()
    {
        return itemrepo.findAll();
    }

    public LibraryItem createNewItem(LibraryItem item) {
        if (item instanceof Book) {
            return bookrepo.save((Book) item);  // ✅ Ensures Book is saved as Book
        }
        return itemrepo.save(item);
    }

    public Book createNewBook(Book book) {
        return bookrepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookrepo.findAll();
    }
}
