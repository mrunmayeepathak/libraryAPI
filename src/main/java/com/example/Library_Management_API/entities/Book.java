package com.example.Library_Management_API.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Book extends LibraryItem {


 Long isbn;
 String author;
 public Book()
 {
  super();
 }
 public Book( int availableCopies, Long isbn, String author) {
  super( "Book",availableCopies);
  this.isbn = isbn;
  this.author = author;
 }





 public void setAuthor(String author)
 {
   this.author = author;
 }
 public String getAuthor()
 {
  return author;
 }


 public void setisbn(Long isbn)
 {
  this.isbn = isbn;
 }
 public Long  getisbn()
 {
  return isbn;
 }

}
