package com.example.Library_Management_API.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BorrowedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name = "itemId")
    LibraryItem item;

    LocalDateTime borrowedDate;
    LocalDateTime returnDate;
public BorrowedRecord()
{

}
    boolean returned;
    public BorrowedRecord(User user, LibraryItem item)
    {
        this.borrowedDate = LocalDateTime.now();
        this.user = user;
        this.item = item;
        this.returned = false;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryItem getItem() {
        return item;
    }

    public void setItem(LibraryItem item) {
        this.item = item;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}

