package com.owaissalam.LibraryManagement.controller;

import com.owaissalam.LibraryManagement.model.Transaction;
import com.owaissalam.LibraryManagement.service.TransactionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/transaction/show/{id}")
    public ResponseEntity<List<Transaction>> showBorrowed(@PathVariable("id") int userID){
        List<Transaction> transactions = transactionService.showBorrowed(userID);
        return ResponseEntity.ok().body(transactions);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> borrow(@RequestParam int userID, @RequestParam int bookID){
        Transaction tx = transactionService.borrowBook(userID, bookID);
        return new ResponseEntity<>(tx, HttpStatus.FOUND);
    }

    @PostMapping("/transaction/return/{id}")
    public ResponseEntity<String> returnBook(@PathVariable("id") int transactionID){
        if(transactionService.returnBook(transactionID)){
            return ResponseEntity.accepted().body("Returned");
        }
        else{
            return ResponseEntity.ok().body("Already returned");
        }
    }
}
