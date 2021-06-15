package com.epam.practice.practice.rest;

import com.epam.practice.practice.model.Weather;
import com.epam.practice.practice.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather/")
public class WeatherRestController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Weather> getWeather(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Weather weather = this.weatherService.getById(id);

        if (weather == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Weather> addWeather(@RequestBody Weather weather) {
        HttpHeaders headers = new HttpHeaders();

        if (weather == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.weatherService.add(weather);
        return new ResponseEntity<>(weather, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Weather> deleteWeather(@PathVariable("id") Long id) {
        Weather weather = this.weatherService.getById(id);

        if (weather == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.weatherService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Weather>> getAllCustomers() {
        List<Weather> weathers = this.weatherService.getAll();

        if (weathers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(weathers, HttpStatus.OK);
    }
}