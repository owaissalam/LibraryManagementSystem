package com.owaissalam.LibraryManagement.service;

import com.owaissalam.LibraryManagement.model.Author;
import com.owaissalam.LibraryManagement.model.Book;
import com.owaissalam.LibraryManagement.repository.AuthorRepository;
import com.owaissalam.LibraryManagement.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(int id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " doesn't exist"));
    }

    public Book updateBook(int id, Book book){
        Book updatedBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " doesn't exist"));
        updatedBook.setISBN(book.getISBN());
        updatedBook.setTitle(book.getTitle());
        updatedBook.setAvailableCopies(book.getAvailableCopies());
        updatedBook.setTotalCopies(book.getTotalCopies());
        bookRepository.save(updatedBook);
        return updatedBook;
    }

    public boolean deleteBook(int id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addAuthorToBook(int bookID, int authorID){
        Book book = bookRepository.findById(bookID)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + bookID + " doesn't exist"));
        Author author = authorRepository.findById(authorID)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + authorID + " doesn't exist"));
        book.addAuthor(author);
        bookRepository.save(book);

    }

    public void removeAuthorFromBook(int bookID, int authorID){
        Book book = bookRepository.findById(bookID)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + bookID + " doesn't exist"));
        Author author = authorRepository.findById(authorID)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + authorID + " doesn't exist"));
        book.removeAuthor(author);
        bookRepository.save(book);
    }
}
