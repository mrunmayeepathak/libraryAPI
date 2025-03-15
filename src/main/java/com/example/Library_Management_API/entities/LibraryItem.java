package com.example.Library_Management_API.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "itemType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "Book"),
})
public abstract class LibraryItem {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;  // ✅ Ensure this is "id"

  String itemType; // ✅ Ensure it's "itemType" (camelCase)

  int availableCopies; // ✅ Ensure it's "availableCopies" (camelCase)
 public LibraryItem(Long id, String itemType, int availableCopies) {
  this.id = id;
  this.itemType = itemType;
  this.availableCopies = availableCopies;
 }

 public String getItemType() { return itemType; }
 public void setItemType(String itemType) { this.itemType = itemType; }

 public int getAvailableCopies() { return availableCopies; }
 public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
}
