package com.epam.practice.practice.model;

import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

@Entity
public class WeatherInfo {

    @Id
    private String city_name;
    private double temp, temp_feels, temp_max, temp_min;
    private int pressure, humidity;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_feels() {
        return temp_feels;
    }

    public void setTemp_feels(double temp_feels) {
        this.temp_feels = temp_feels;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public WeatherInfo(){}
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();
        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch (Exception e) {
            System.out.println("Такого города нет");
        }
        return content.toString();
    }

    public WeatherInfo(String city_name) {
        this.city_name = city_name;
        String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + city_name  + "&APPID=65e9936634de7608cc4a6afd5514a85c");
        if(!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            temp = obj.getJSONObject("main").getDouble("temp");
            temp_min = obj.getJSONObject("main").getDouble("temp_min");
            temp_max = obj.getJSONObject("main").getDouble("temp_max");
            temp_feels =obj.getJSONObject("main").getDouble("feels_like");
            pressure = obj.getJSONObject("main").getInt("pressure");
            humidity = obj.getJSONObject("main").getInt("temp");
        }


    }
}
