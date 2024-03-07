package com.nurali.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.nurali.weather.entity.Coordinates;
import com.nurali.weather.exceptions.WeatherServiceException;
import com.nurali.weather.dto.WeatherResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${api.weather}")
    private String apiUrl;

    @Value("${api.climate_forecast}")
    private String climateForecastUrl;

    @Value("${api.geo}")
    private String geoApiUrl;

    public WeatherService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public WeatherResponseDTO getDailyWeatherReport(String country, String city) throws WeatherServiceException {
        Coordinates coordinates = getCoordinates(country, city);
        return getDailyWeatherReport(coordinates.getLat(), coordinates.getLon());
    }

    public WeatherResponseDTO getWeeklyWeatherReport(String country, String city) throws WeatherServiceException {
        Coordinates coordinates = getCoordinates(country, city);
        return getWeeklyWeatherReport(coordinates.getLat(), coordinates.getLon());
    }

    public WeatherResponseDTO getMonthlyWeatherReport(String country, String city) throws WeatherServiceException {
        Coordinates coordinates = getCoordinates(country, city);
        return getMonthlyWeatherReport(coordinates.getLat(), coordinates.getLon());
    }

    private WeatherResponseDTO getDailyWeatherReport(double lat, double lon) throws WeatherServiceException {
        String url = apiUrl + "?lat=" + lat + "&lon=" + lon + "&exclude=minutely,hourly,alerts&appid=" + apiKey;
        return fetchWeatherData(url);
    }

    private WeatherResponseDTO getWeeklyWeatherReport(double lat, double lon) throws WeatherServiceException {
        String url = climateForecastUrl + "?lat=" + lat + "&lon=" + lon + "&cnt=7&appid=" + apiKey;
        return fetchWeatherData(url);
    }

    private WeatherResponseDTO getMonthlyWeatherReport(double lat, double lon) throws WeatherServiceException {
        String url = climateForecastUrl + "?lat=" + lat + "&lon=" + lon + "&cnt=30&appid=" + apiKey;
        return fetchWeatherData(url);
    }

    private WeatherResponseDTO fetchWeatherData(String url) throws WeatherServiceException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();
        try {
            return objectMapper.readValue(responseBody, WeatherResponseDTO.class);
        } catch (IOException e) {
            throw new WeatherServiceException("Error parsing weather response", e);
        }
    }

    private Coordinates getCoordinates(String country, String city) throws WeatherServiceException {
        // Geocoding API call to get coordinates
        String geoUrl = geoApiUrl + "?q=" + city + "," + country + "&limit=1&appid=" + apiKey;
        ResponseEntity<String> geoResponseEntity = restTemplate.getForEntity(geoUrl, String.class);
        String geoResponseBody = geoResponseEntity.getBody();

        // Extract latitude and longitude from geocoding response
        try {
            JsonNode geoJsonNode = objectMapper.readTree(geoResponseBody);
            double lat = geoJsonNode.get(0).get("lat").asDouble();
            double lon = geoJsonNode.get(0).get("lon").asDouble();
            return new Coordinates(lat, lon);
        } catch (IOException | NullPointerException e) {
            throw new WeatherServiceException("Error parsing geocoding response", (IOException) e);
        }
    }
}
