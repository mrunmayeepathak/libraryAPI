package com.example.Library_Management_API.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Regular extends User{
    public Regular()
    {
        super();
    }

    public Regular(String name , String email )
    {
        super(name, email,"Regular",4);

    }


}
