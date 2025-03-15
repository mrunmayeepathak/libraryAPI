package com.example.Library_Management_API.service;

import com.example.Library_Management_API.entities.LibraryItem;
import com.example.Library_Management_API.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {

    private final ItemRepository itemrepo;

    public LibraryService(ItemRepository itemrepo)
    {
        this.itemrepo = itemrepo;
    }


    public List<LibraryItem> getallItems() {
   return itemrepo.findAll();

    }
}
