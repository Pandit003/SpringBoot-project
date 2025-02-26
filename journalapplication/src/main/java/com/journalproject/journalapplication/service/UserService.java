package com.journalproject.journalapplication.service;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.journalproject.journalapplication.entity.User;
import com.journalproject.journalapplication.repository.UserRepository;
@Component
public class UserService {
    @Autowired
    private UserRepository UserRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        UserRepository.save(user);
    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        UserRepository.save(user);
    }

    public void saveUser(User user){
        UserRepository.save(user);
    }

    public void deleteById(ObjectId id){
        UserRepository.deleteById(id);
    }
    public List<User> getAllUser(){
        return UserRepository.findAll();
    }

    public User findByUserName(String userName){
        return UserRepository.findByUserName(userName);
    }

}
