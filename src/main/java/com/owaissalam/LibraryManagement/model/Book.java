package com.owaissalam.LibraryManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String ISBN;
    private int totalCopies;
    private int availableCopies;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "author_book",
            joinColumns = {
                    @JoinColumn(name = "book_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")
            }
    )
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public void removeAuthor(Author author){
        this.authors.remove(author);
    }

}
