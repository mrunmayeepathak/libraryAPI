package com.example.Library_Management_API.Controller;

import com.example.Library_Management_API.DTO.ItemDTO;
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


    @PostMapping("/add") // Added path
    public LibraryItem createNewItem(@RequestBody LibraryItem item) {
        return libservice.createNewItem(item);
    }
}
