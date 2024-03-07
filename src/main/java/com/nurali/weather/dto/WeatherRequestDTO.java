package com.nurali.weather.dto;

import com.nurali.weather.enums.WeatherTimeRange;
import lombok.Data;

@Data
public class WeatherRequestDTO {
    private String country;
    private String city;
    private WeatherTimeRange timeRange; // Enum: DAILY, WEEKLY, MONTHLY
    private String appid;
}
