package com.nurali.weather.exceptions;

import java.io.IOException;

public class WeatherServiceException extends Throwable {
    public WeatherServiceException(String errorParsingWeatherResponse, IOException e) {
    }
}
