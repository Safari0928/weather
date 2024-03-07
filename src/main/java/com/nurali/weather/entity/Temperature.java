package com.nurali.weather.entity;

import lombok.Data;

@Data
public class Temperature {
    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;
}
