package com.nurali.weather.controller;

import com.nurali.weather.dto.WeatherResponseDTO;
import com.nurali.weather.exceptions.WeatherServiceException;
import com.nurali.weather.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/daily")
    public ResponseEntity<WeatherResponseDTO> getDailyWeather(@RequestParam String country, @RequestParam String city) {
        try {
            WeatherResponseDTO weatherResponseDTO = weatherService.getDailyWeatherReport(country, city);
            return ResponseEntity.ok(weatherResponseDTO);
        } catch (WeatherServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/weekly")
    public ResponseEntity<WeatherResponseDTO> getWeeklyWeather(@RequestParam String country, @RequestParam String city) {
        try {
            WeatherResponseDTO weatherResponseDTO = weatherService.getWeeklyWeatherReport(country, city);
            return ResponseEntity.ok(weatherResponseDTO);
        } catch (WeatherServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/monthly")
    public ResponseEntity<WeatherResponseDTO> getMonthlyWeather(@RequestParam String country, @RequestParam String city) {
        try {
            WeatherResponseDTO weatherResponseDTO = weatherService.getMonthlyWeatherReport(country, city);
            return ResponseEntity.ok(weatherResponseDTO);
        } catch (WeatherServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
