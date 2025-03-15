package com.example.Library_Management_API.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book extends LibraryItem {


 Long ISBN;
 String author;
 public Book(Long id, int availableCopies, Long ISBN, String author) {
  super(id, "Book", availableCopies);  // ✅ Set itemType
  this.ISBN = ISBN;
  this.author = author;
 }

}
