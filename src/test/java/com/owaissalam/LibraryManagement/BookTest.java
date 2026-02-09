package com.owaissalam.LibraryManagement;

import com.owaissalam.LibraryManagement.model.Author;
import com.owaissalam.LibraryManagement.model.Book;
import com.owaissalam.LibraryManagement.repository.AuthorRepository;
import com.owaissalam.LibraryManagement.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class BookTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Test
    public void testAuthorCreation(){
//        Author author = Author
//                .builder()
//                .name("Daniel")
//                .email("daniel@example.com")
//                .build();

        System.out.println(bookRepository.findAll());

//        Book book = new Book();
//        book.setTitle("Clean Code");
//        book.setISBN("XE34637GH");
//        book.setTotalCopies(560);
//        book.setAvailableCopies(392);
//        book.addAuthor(author);
//
//        bookRepository.save(book);
//        System.out.println("Saved in DB");
    }
}
