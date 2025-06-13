package com.journalproject.journalapplication.service;

import com.journalproject.journalapplication.api.response.WeatherResponse;
import com.journalproject.journalapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apikey = "";
    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=API_KEY";

    @Autowired
    RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){

        //if you want to post in any api then create httpEntity
        //for using builder use builder tag in after use lombok in entity class

        // below commented code is for post method
        /*HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key","value");

        User user = User.builder().userName("pandit").password("pandit123").build();
        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(API, HttpMethod.POST,httpEntity, WeatherResponse.class);*/



        String finalapi = API.replace("CITY",city).replace("API_KEY",apikey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
