package com.owaissalam.LibraryManagement.service;

import com.owaissalam.LibraryManagement.model.Book;
import com.owaissalam.LibraryManagement.model.Transaction;
import com.owaissalam.LibraryManagement.model.TransactionStatus;
import com.owaissalam.LibraryManagement.model.User;
import com.owaissalam.LibraryManagement.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BookService bookService;
    private final UserService userService;

    public List<Transaction> showBorrowed(int userID){
        return transactionRepository.findByUserIdAndStatus(userID, TransactionStatus.BORROWED);
    }

    public Transaction borrowBook(int userID, int bookID){
        User user = userService.getUserById(userID);
        Book book = bookService.getBookById(bookID);
        Transaction tx = new Transaction();
        LocalDate today = LocalDate.now();

        tx.setBorrowDate(today);
        tx.setDueDate(today.plusDays(14));
        tx.setStatus(TransactionStatus.BORROWED);
        tx.setReturnDate(null);
        tx.addUser(user);
        tx.addBook(book);
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        transactionRepository.save(tx);
        return tx;
    }

    public boolean returnBook(int transactionID){
        Transaction tx = transactionRepository.findById(transactionID)
                .orElseThrow(() -> new RuntimeException("no transaction with id " + transactionID + " exists"));
        if(tx.getStatus().equals(TransactionStatus.RETURNED)){
            return false;
        }

        tx.setStatus(TransactionStatus.RETURNED);
        tx.setReturnDate(LocalDate.now());
        tx.getBook().setAvailableCopies(tx.getBook().getAvailableCopies() + 1);
        transactionRepository.save(tx);
        return true;

    }

}
