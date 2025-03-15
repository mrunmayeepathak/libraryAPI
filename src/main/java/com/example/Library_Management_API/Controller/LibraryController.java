package com.example.Library_Management_API.Controller;

import java.util.*;

import com.example.Library_Management_API.entities.LibraryItem;
import com.example.Library_Management_API.service.LibraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libservice;

    public LibraryController(LibraryService libservice)
    {
        this.libservice = libservice;
    }


    @GetMapping("/items")
    public List<LibraryItem> getallitems(){

        return libservice.getallItems();


    }
}
