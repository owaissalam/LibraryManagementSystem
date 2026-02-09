package com.owaissalam.LibraryManagement.repository;

import com.owaissalam.LibraryManagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
