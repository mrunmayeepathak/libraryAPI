package com.example.Library_Management_API.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends User{
    public Student()
    {
        super();
    }
    public Student(String name , String email)
    {
        super(name, email, "Student", 6);
    }
}
