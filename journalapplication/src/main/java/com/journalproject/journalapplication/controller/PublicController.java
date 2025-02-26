package com.journalproject.journalapplication.controller;

import com.journalproject.journalapplication.entity.User;
import com.journalproject.journalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
    @GetMapping
    public ResponseEntity<?> printhello(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
