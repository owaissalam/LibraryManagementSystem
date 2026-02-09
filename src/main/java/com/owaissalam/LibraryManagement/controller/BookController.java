package com.owaissalam.LibraryManagement.controller;

import com.owaissalam.LibraryManagement.model.Book;
import com.owaissalam.LibraryManagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class  BookController {
    private final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<Book> create(@RequestBody Book book){
        Book createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAll(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.FOUND).body(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> get(@PathVariable("id") int bookID){
        Book book = bookService.getBookById(bookID);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") int bookID, @RequestBody Book book){
            Book updatedBook = bookService.updateBook(bookID, book);
            return ResponseEntity.ok().body(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity delete(@PathVariable("id") int bookID){
        if(bookService.deleteBook(bookID)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/books/{book_id}/authors/{author_id}")
    public ResponseEntity addAuthorToBook(
            @PathVariable("book_id") int bookID,
            @PathVariable("author_id") int authorID
            ){
        bookService.addAuthorToBook(authorID, bookID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("books/{book_id}/authors/{author_id}")
    public ResponseEntity removeAuthorFromBook(
            @PathVariable("book_id") int bookID,
            @PathVariable("author_id") int authorID){
        bookService.removeAuthorFromBook(authorID, bookID);
        return ResponseEntity.ok().build();
    }

}
