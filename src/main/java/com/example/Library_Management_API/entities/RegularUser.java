package com.example.Library_Management_API.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class RegularUser extends User{

    public RegularUser(String name , String email)
    {
        super(name, email,4);

    }


}
