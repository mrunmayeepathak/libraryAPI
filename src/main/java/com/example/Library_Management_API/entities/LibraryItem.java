package com.example.Library_Management_API.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LibraryItem {


 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 Long id;
 String ItemType;
 int availableCopies;
}
