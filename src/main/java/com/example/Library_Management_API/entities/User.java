package com.example.Library_Management_API.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
