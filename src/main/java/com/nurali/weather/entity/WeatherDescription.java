package com.nurali.weather.entity;

import lombok.Data;

@Data
public class WeatherDescription {
    private int id;
    private String main;
    private String description;

    public WeatherDescription(int id, String main, String description) {
        this.id = id;
        this.main = main;
        this.description = description;
    }
}
