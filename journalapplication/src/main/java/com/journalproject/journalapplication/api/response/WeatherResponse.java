package com.journalproject.journalapplication.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse{
    private main main;

    @Getter
    @Setter
    public static class main {
        public double temp;
        @JsonProperty("feels_like")
        public double feelsLike;
        @JsonProperty("temp_min")
        public double tempMin;
        @JsonProperty("temp_max")
        public double tempMax;
        public int pressure;
        public int humidity;
        @JsonProperty("sea_level")
        public int seaLevel;
    }
}