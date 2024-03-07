package com.nurali.weather.entity;

import lombok.Data;

@Data
public class Coordinates {

    private double lat;
    private double lon;

    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
