package com.owaissalam.LibraryManagement.controller;

import com.owaissalam.LibraryManagement.model.Author;
import com.owaissalam.LibraryManagement.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<Author> create(@RequestBody Author author){
        Author createdAuthor = authorService.addAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAll(){
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.FOUND);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> get(@PathVariable("id") int authorID){
        Author author = authorService.getAuthorById(authorID);
        return ResponseEntity.status(HttpStatus.FOUND).body(author);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") int authorID, @RequestBody Author author){
        Author updatedAuthor = authorService.updateAuthor(authorID, author);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAuthor);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity delete(@PathVariable("id") int authorID){
        if(authorService.deleteAuthor(authorID)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
