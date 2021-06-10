package com.epam.practice.web_meteo;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

@Controller
public class WeatherController {

    public static final double K = 272.1;

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

    @RequestMapping(value = "")
    public String weatherController(@RequestParam(name = "name", required = false, defaultValue = "Minsk") String name, Model model) {
        String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + name  + "&APPID=65e9936634de7608cc4a6afd5514a85c");
        if(!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            model.addAttribute("name", name);
            model.addAttribute("temp", new DecimalFormat("#.##").format(obj.getJSONObject("main").getDouble("temp") - K));
            model.addAttribute("temp_feels", new DecimalFormat("#.##").format(obj.getJSONObject("main").getDouble("feels_like") - K));
            model.addAttribute("temp_max", new DecimalFormat("#.##").format(obj.getJSONObject("main").getDouble("temp_max") - K));
            model.addAttribute("temp_min", new DecimalFormat("#.##").format(obj.getJSONObject("main").getDouble("temp_min") - K));
            model.addAttribute("pres", obj.getJSONObject("main").getDouble("pressure"));
        }
        return "getweather";
    }
}
