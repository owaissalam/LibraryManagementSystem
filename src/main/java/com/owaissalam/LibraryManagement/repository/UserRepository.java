package com.owaissalam.LibraryManagement.repository;

import com.owaissalam.LibraryManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
