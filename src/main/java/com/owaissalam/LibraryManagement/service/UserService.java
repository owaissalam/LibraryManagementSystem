package com.owaissalam.LibraryManagement.service;

import com.owaissalam.LibraryManagement.model.User;
import com.owaissalam.LibraryManagement.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id : " + id + " doesn't exist"));
    }

    public User updateUser(int id, User user){
        User updatedUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id : " + id + " doesn't exist"));
        updatedUser.setEmail(user.getEmail());
        updatedUser.setName(user.getName());
        updatedUser.setGender(user.getGender());

        userRepository.save(updatedUser);
        return updatedUser;
    }

    public boolean deleteUser(int id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
