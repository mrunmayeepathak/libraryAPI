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
        @JsonSubTypes.Type(value = DVD.class, name = "DVD"),
        @JsonSubTypes.Type(value = Journal.class, name = "Journal")
})
public abstract class LibraryItem {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
  Long itemId;


 String itemType;


 int availableCopies;

 public LibraryItem() {
  this.itemType = this.getClass().getSimpleName();
 }

 public LibraryItem(String itemType, int availableCopies) {
  this.itemType = itemType;
  this.availableCopies = availableCopies;
 }



public Long getItemId ()
{
 return itemId;
}
public void setItemId(Long itemId)
{
  this.itemId = itemId;
}
public String getItemType()
{
 return itemType;
}
public void setItemType(String itemType)
{
 this.itemType = itemType;
}
 public int getAvailableCopies() { return availableCopies; }
 public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
}
