package com.example.Library_Management_API.repository;

import com.example.Library_Management_API.entities.DVD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DVDrepository extends JpaRepository<DVD, Long> {
}
