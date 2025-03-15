package com.example.Library_Management_API.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ItemDTO {


    private Long id;
   private  String ItemType;
    private int availableCopies;

}
