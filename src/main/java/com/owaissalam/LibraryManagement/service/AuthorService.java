package com.owaissalam.LibraryManagement.service;

import com.owaissalam.LibraryManagement.model.Author;
import com.owaissalam.LibraryManagement.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id : " + id + " doesn't exist"));
    }

    public Author updateAuthor(int id, Author author){
                Author updatedAuthor = authorRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Author with id " + id + " doesn't exist"));
                updatedAuthor.setName(author.getName());
                updatedAuthor.setEmail(author.getEmail());
                authorRepository.save(updatedAuthor);
                return updatedAuthor;
    }

    public boolean deleteAuthor(int id){
        if(authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
