package com.epam.practice.practice.repository;

import com.epam.practice.practice.model.WeatherInfo;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<WeatherInfo, String> {
}
