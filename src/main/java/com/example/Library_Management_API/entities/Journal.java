package com.example.Library_Management_API.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Journal extends LibraryItem {



    Long issn;
    String writer;
    public Journal()
    {
        super();

    }
    public Journal(int availableCopies, Long issn, String writer) {
        super("Journal", availableCopies);
        this.issn = issn;
        this.writer = writer;
    }

    public void setissn(Long issn)
    {
        this.issn = issn;
    }
    public Long  getissn()
    {
        return issn;
    }
    public void setwriter(String writer)
    {
        this.writer = writer;
    }
    public String getwriter()
    {
        return writer;
    }


}
