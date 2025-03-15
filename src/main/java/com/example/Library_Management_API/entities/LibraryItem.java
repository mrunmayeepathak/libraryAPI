package com.example.Library_Management_API.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LibraryItem {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;  // ✅ Ensure this is "id"

  String itemType; // ✅ Ensure it's "itemType" (camelCase)

  int availableCopies; // ✅ Ensure it's "availableCopies" (camelCase)

 public String getItemType() { return itemType; }
 public void setItemType(String itemType) { this.itemType = itemType; }

 public int getAvailableCopies() { return availableCopies; }
 public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
}
