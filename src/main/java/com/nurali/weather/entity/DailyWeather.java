package com.nurali.weather.entity;

import lombok.Data;

import java.util.List;

@Data
public class DailyWeather {
    private long dt;
    private long sunrise;
    private long sunset;
    private Temperature temp;
    private double feels_like;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double wind_speed;
    private int wind_deg;
    private List<WeatherDescription> weather;
    private int clouds;
    private double pop;
    private double rain;
}
