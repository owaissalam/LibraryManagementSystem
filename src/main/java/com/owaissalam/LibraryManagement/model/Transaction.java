package com.owaissalam.LibraryManagement.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    @ManyToOne
    @Setter(AccessLevel.NONE)
    private User user;

    @ManyToOne
    @Setter(AccessLevel.NONE)
    private Book book;

    public void addBook(Book book){
        this.book = book;
    }
    public void addUser(User user){
        this.user = user;
    }
}
