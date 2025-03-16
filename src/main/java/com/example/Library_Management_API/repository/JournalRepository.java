package com.example.Library_Management_API.repository;

import com.example.Library_Management_API.entities.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long > {
}
