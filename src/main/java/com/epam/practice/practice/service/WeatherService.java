package com.epam.practice.practice.service;

import com.epam.practice.practice.model.Weather;

import java.util.List;

public interface WeatherService {

    Weather getById(Long id);

    void add(Weather weather);

    void delete(Long id);

    List<Weather> getAll();
}
