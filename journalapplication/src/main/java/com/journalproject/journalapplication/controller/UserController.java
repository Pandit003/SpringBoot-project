package com.journalproject.journalapplication.controller;

import java.util.List;

import com.journalproject.journalapplication.api.response.WeatherResponse;
import com.journalproject.journalapplication.repository.UserRepository;
import com.journalproject.journalapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.journalproject.journalapplication.entity.User;
import com.journalproject.journalapplication.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUser() {
//        return userService.getAllUser();
//    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Bhubaneswar");
        String greeting = "";
        if(weatherResponse!=null){
            greeting = ", Weather feels like "+ weatherResponse.getMain().feelsLike;
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting,HttpStatus.OK);
    }

}

//user id : pandit
//pass : pandit123
