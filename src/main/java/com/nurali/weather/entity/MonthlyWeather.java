package com.nurali.weather.entity;

import lombok.Data;

import java.util.List;

@Data
public class MonthlyWeather {
    private long dt;
    private Temperature temp;
    private double feelsLike;
    private int pressure;
    private int humidity;
    private double dewPoint;
    private double windSpeed;
    private int windDeg;
    private List<WeatherDescription> weather;
    private int clouds;
    private double pop;
    private double rain;
}
