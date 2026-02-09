package com.owaissalam.LibraryManagement.controller;

import com.owaissalam.LibraryManagement.model.User;
import com.owaissalam.LibraryManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user){
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable("id") int userID){
        User user = userService.getUserById(userID);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable("id") int userID, @RequestBody User user){
        User updatedUser = userService.updateUser(userID, user);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable("id") int userID){
        if(userService.deleteUser(userID)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
