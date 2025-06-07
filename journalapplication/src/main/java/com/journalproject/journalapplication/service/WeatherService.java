package com.journalproject.journalapplication.service;

import com.journalproject.journalapplication.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apikey = "bdb3741061c5513490c711824003395c";
    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=API_KEY";

    @Autowired
    RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalapi = API.replace("CITY",city).replace("API_KEY",apikey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
