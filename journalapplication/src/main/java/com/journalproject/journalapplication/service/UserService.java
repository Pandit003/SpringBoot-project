package com.journalproject.journalapplication.service;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.journalproject.journalapplication.entity.User;
import com.journalproject.journalapplication.repository.UserRepository;
@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository UserRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger loger = LoggerFactory.getLogger(UserService.class);
//    @Slf4j using this notation no need to initialize this object


    public void saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            UserRepository.save(user);
        }catch (Exception e){
//            loger.error("Error occured "+e);  // use when logger initialized
            log.error("Error occured "+e);
            log.warn("Error occured "+e);
            log.info("Error occured "+e);
            log.debug("Error occured "+e);
        }
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
