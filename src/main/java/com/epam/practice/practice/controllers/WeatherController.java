package com.epam.practice.practice.controllers;

import com.epam.practice.practice.model.WeatherInfo;
import com.epam.practice.practice.repository.WeatherRepository;
import com.epam.practice.practice.services.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class WeatherController {

    @Autowired
    WeatherRepository weatherRepository;
    private final SchedulerService schedulerService;

    public WeatherController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }


    @GetMapping("/weather-list")
    public String weatherList(Model model) throws InterruptedException {
        model.addAttribute("weatherInfos",schedulerService.doWork());
        return  "weather-list";
    }
    @GetMapping("/weather-list/add")
    public String add(Model model){
        model.addAttribute("main","Главная страница погодки");
        return "city-add";
    }
    @PostMapping("/weather-list/add")
    public String weatherAdd(@RequestParam String city_name, Model model){
        WeatherInfo weatherInfo = new WeatherInfo(city_name);
        weatherRepository.save(weatherInfo);
        return "redirect:/weather-list";
    }

    @GetMapping("/weather-list/{city_name}")
    public String getCityWeather(@PathVariable (value = "city_name") String city_name, Model model) {
        if (!weatherRepository.existsById(city_name)) {
            return "redirct:/weather-list";
        }
        Optional<WeatherInfo> weatherInfo = weatherRepository.findById(city_name);
        ArrayList<WeatherInfo> res = new ArrayList<>();
        weatherInfo.ifPresent(res::add);
        model.addAttribute("weatherInfo", res);
        return "city-weather";
    }
    @GetMapping("/weather-list/remove")
    public String remove(Model model){
        model.addAttribute("main","Главная страница погодки");
        return "remove-city";
    }
    @PostMapping("/weather-list/remove")
    public String cityRemove(@RequestParam String city_name, Model model){
        WeatherInfo weatherInfo = new WeatherInfo(city_name);
        weatherRepository.delete(weatherInfo);
        return "redirect:/weather-list";
    }

}
