package com.example.Library_Management_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Library_Management_API.entities.LibraryItem;

public interface ItemRepository extends  JpaRepository<LibraryItem,Long> {

}
