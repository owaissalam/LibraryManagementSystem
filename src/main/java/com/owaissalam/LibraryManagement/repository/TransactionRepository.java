package com.owaissalam.LibraryManagement.repository;

import com.owaissalam.LibraryManagement.model.Transaction;
import com.owaissalam.LibraryManagement.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserIdAndStatus(int userId, TransactionStatus status);
}
