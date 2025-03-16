package com.example.Library_Management_API.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Premium extends User{
    public Premium()
    {
        super();
    }
    public Premium(String name, String email)
    {
        super(name, email, "Premium", 10);
    }
}
