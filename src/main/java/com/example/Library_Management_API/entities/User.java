package com.example.Library_Management_API.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    String name;
    String email;
    int maxBorrow=0;
    public User(String name , String email, int maxBorrow)
    {
        this.name = name;
        this.email = email;
        this.maxBorrow = maxBorrow;
    }
}
