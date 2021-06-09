package com.epam.practice;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Scanner;


public class App 
{
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

    public static void getWeather(String getCity){
        String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getCity  + "&APPID=65e9936634de7608cc4a6afd5514a85c");
        if(!output.isEmpty()){
            JSONObject obj = new JSONObject(output);

            System.out.println("Погода в городе " + getCity + ": ");
            System.out.println("Температура: " + new DecimalFormat("#.##").format(obj.getJSONObject("main").getDouble("temp") - K) + " C");
            System.out.println("Ощущается: " + new DecimalFormat("#.##").format(obj.getJSONObject("main").getDouble("feels_like") - K) + " C");
            System.out.println("Максимум: " + new DecimalFormat("#.##").format(obj.getJSONObject("main").getDouble("temp_max") - K) + " C");
            System.out.println("Минимум: " + new DecimalFormat("#.##").format(obj.getJSONObject("main").getDouble("temp_min") - K) + " C");
            System.out.println("Давление: " + obj.getJSONObject("main").getDouble("pressure") + " мм. рт. столба");
        }
    }



    public static void main( String[] args )
    {

        Scanner sc = new Scanner(System.in);

        while(true) {
            String getCity = sc.nextLine();
            if("q".equals(getCity)) {
                break;
            }
            else getWeather(getCity);

        }


    }
}
