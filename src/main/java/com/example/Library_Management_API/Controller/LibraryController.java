package com.example.Library_Management_API.Controller;

import java.util.*;

import com.example.Library_Management_API.entities.LibraryItem;
import com.example.Library_Management_API.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libservice;
@Autowired
    public LibraryController(LibraryService libservice)
    {
        this.libservice = libservice;
    }


    @GetMapping("/items")
    public List<LibraryItem> getallitems(){

        return libservice.getallItems();
    }

    @PostMapping
    public LibraryItem createNewItem(@RequestBody LibraryItem item)
    {
       return libservice.createNewItem(item);
    }
}
