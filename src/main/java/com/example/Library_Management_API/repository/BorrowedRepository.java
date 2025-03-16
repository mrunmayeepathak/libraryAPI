package com.example.Library_Management_API.repository;

import com.example.Library_Management_API.entities.BorrowedRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedRepository extends JpaRepository<BorrowedRecord, Long > {
}
