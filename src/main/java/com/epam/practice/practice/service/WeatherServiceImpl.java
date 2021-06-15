package com.epam.practice.practice.service;

import com.epam.practice.practice.model.Weather;
import com.epam.practice.practice.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService{
    @Autowired
    WeatherRepository weatherRepository;

    @Override
    public Weather getById(Long id) {
        log.info("In WeatherServiceImpl getById {}", id);
        return weatherRepository.findById(id).get();
    }

    @Override
    public void add(Weather weather) {
            log.info("In WeatherServiceImpl add {}", weather);
            weatherRepository.save(weather);

    }

    @Override
    public void delete(Long id) {
        log.info("In WeatherServiceImpl delete {}", id);
        weatherRepository.deleteById(id);

    }

    @Override
    public List<Weather> getAll() {
        log.info("In WeatherServiceImpl getAll");
        return weatherRepository.findAll();
    }
}
