package com.nurali.weather.dto;

import com.nurali.weather.entity.DailyWeather;
import com.nurali.weather.entity.MonthlyWeather;
import com.nurali.weather.entity.WeeklyWeather;
import com.nurali.weather.enums.WeatherTimeRange;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponseDTO {
    private String country;
    private String city;
    private WeatherTimeRange timeRange;
    private List<DailyWeather> dailyWeather; // For daily report
    private List<WeeklyWeather> weeklyWeather; // For weekly report
    private List<MonthlyWeather> monthlyWeather; // For monthly report
}
