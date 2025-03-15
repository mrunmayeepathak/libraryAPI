package com.example.Library_Management_API.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Book extends LibraryItem {


 Long ISBN;
 String author;
 public Book()
 {
  super();
 }
 public Book(Long id, int availableCopies, Long ISBN, String author) {
  super(id, "Book", availableCopies);
  this.ISBN = ISBN;
  this.author = author;
 }


 public Long  getISBN()
 {
   return ISBN;
 }
 public void setISBN(Long ISBN)
 {
  this.ISBN = ISBN;
 }

 public String getAuthor()
 {
  return author;
 }
 public void setAuthor(String author)
 {
   this.author = author;
 }

}
