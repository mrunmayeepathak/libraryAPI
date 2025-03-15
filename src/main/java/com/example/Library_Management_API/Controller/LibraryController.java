package com.example.Library_Management_API.Controller;

import java.util.*;
import com.example.Library_Management_API.model.LibraryItem;
import com.example.Library_Management_API.service.LibraryService;
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

    public List<LibraryItem> getallitems(){




    }
}
