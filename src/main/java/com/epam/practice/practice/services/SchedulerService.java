package com.epam.practice.practice.services;

import java.util.List;

import com.epam.practice.practice.model.WeatherInfo;
import com.epam.practice.practice.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



@Service
public class SchedulerService {

    @Autowired
    WeatherRepository weatherRepository;
    List<WeatherInfo> weatherInfos = null;

    private static final Logger log = LoggerFactory.getLogger(SchedulerService.class);


    @Async
    @Scheduled(initialDelayString = "${scheduler.delay}", fixedDelayString = "${scheduler.delay}")
    public void doWork() throws InterruptedException {
        log.info("Start process");

        for(WeatherInfo all : weatherRepository.findAll()) {
            log.info(all.toString());
        }
    }
}