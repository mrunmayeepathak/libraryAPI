package com.example.Library_Management_API.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "userType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Regular.class, name = "Regular"),
        @JsonSubTypes.Type(value = Premium.class, name = "Premium"),
        @JsonSubTypes.Type(value = Student.class, name = "Student")
})
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    String name;
    String email;
    String userType;
    int maxBorrow=0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<BorrowedRecord> borrowHistory =  new ArrayList<>();

    boolean suspended = false;
    LocalDateTime suspensionEndDate = LocalDateTime.now();
    int latereturnsinaMonth =0;
    public boolean isSuspended()
    {
        if(suspended && suspensionEndDate.isBefore(LocalDateTime.now()))
        {
            suspended = false;
        }
        return suspended;
    }

    public void setLateReturns() {
        latereturnsinaMonth++;

        if (latereturnsinaMonth > 2) {
            suspended = true;
            suspensionEndDate = LocalDateTime.now().plusDays(7);
        }
    }

    public int getLateReturns()
    {
        return this.latereturnsinaMonth;
    }

    public void suspendforaweek()
    {
        this.suspended = true;
        this.suspensionEndDate = LocalDateTime.now().plusDays(7);
    }


    public User() {
        this.userType = this.getClass().getSimpleName();
    }

    public User(String name , String email, String userType , int maxBorrow)
    {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.maxBorrow = maxBorrow;
    }


    public Long getUserID()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUserType()
    {
        return userType;
    }
    public void setUserType(String userType)
    {
        this.userType = userType;
    }
    public List<BorrowedRecord> getBorrowHistory() {
        return borrowHistory;
    }

    public void setBorrowHistory(List<BorrowedRecord> borrowHistory)
    {
        this.borrowHistory = borrowHistory;
    }

    public int getMaxBorrow() {
        return maxBorrow;
    }
}
