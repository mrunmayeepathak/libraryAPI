package com.example.Library_Management_API.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "itemType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "Book"),
})
public abstract class LibraryItem {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;


 @Column(name = "itemType", nullable = false, updatable = false)
 String itemType;


 int availableCopies;

 public LibraryItem() {
  this.itemType = this.getClass().getSimpleName();
 }

 public LibraryItem(Long id,String itemType, int availableCopies) {
  this.id = id;
  this.itemType = itemType;
  this.availableCopies = availableCopies;
 }




 public int getAvailableCopies() { return availableCopies; }
 public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
}
