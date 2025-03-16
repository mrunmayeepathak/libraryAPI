package com.example.Library_Management_API.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class DVD extends LibraryItem {

   String director;
   Long asin;
    public DVD()
    {
        super();
    }
    public DVD( int availableCopies, Long asin, String director) {
        super("DVD", availableCopies);
        this.asin = asin;
        this.director = director;
    }


    public Long  getasin()
    {
        return asin;
    }
    public void setasin(Long asin)
    {
        this.asin = asin;
    }

    public String getDirector()
    {
        return director ;
    }
    public void setDirector(String director)
    {
        this.director = director;
    }


}
