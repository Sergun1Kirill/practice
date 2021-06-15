package com.epam.practice.practice.repository;

import com.epam.practice.practice.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
