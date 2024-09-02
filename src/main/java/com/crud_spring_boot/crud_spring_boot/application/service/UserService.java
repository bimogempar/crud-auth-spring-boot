package com.crud_spring_boot.crud_spring_boot.application.service;

import com.crud_spring_boot.crud_spring_boot.domain.model.User;
import com.crud_spring_boot.crud_spring_boot.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User updateUser(UUID id, User userDetails){
        return userRepository.findById(id)
                .map(user->{
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(userDetails.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(()->new RuntimeException("User not found"));
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }
}
