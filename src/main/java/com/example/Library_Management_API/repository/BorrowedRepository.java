package com.example.Library_Management_API.repository;

import com.example.Library_Management_API.entities.BorrowedRecord;
import com.example.Library_Management_API.entities.LibraryItem;
import com.example.Library_Management_API.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedRepository extends JpaRepository<BorrowedRecord, Long > {
    List<BorrowedRecord> findByUserAndItemAndReturnedFalse(User user, LibraryItem item);

    int countByUserAndReturnedFalse(User user);
}
