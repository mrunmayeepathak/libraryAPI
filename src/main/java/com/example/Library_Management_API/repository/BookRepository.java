package com.example.Library_Management_API.repository;

import com.example.Library_Management_API.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long > {
}
