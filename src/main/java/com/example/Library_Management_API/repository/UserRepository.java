package com.example.Library_Management_API.repository;

import com.example.Library_Management_API.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long > {
}
