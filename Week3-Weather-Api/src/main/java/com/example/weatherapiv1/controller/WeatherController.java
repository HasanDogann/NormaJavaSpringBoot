package com.example.weatherapiv1.controller;



import com.example.weatherapiv1.model.currentModel.RootNow;
import com.example.weatherapiv1.model.forecastModel.RootModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Validated
@RestController
@RequestMapping("api")
public class WeatherController {
    private String url;

    //RestTemplate Injection
    private RestTemplate restTemplate;
    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //This function converts timestamp to date format
    public void formatDate(long timestamp){
        long unixTime=timestamp;
        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        final String formattedDtm = Instant.ofEpochSecond(unixTime)
                .atZone(ZoneId.of("GMT-3"))
                .format(formatter);
        System.out.println("Date is: "+formattedDtm);     // => '2013-06-27 09:31:00'
    }


    //This GET method shows 2 days weather forecast
    @GetMapping("weather/forecast")
    public ResponseEntity<?> apiConnection(@RequestParam("lat") double lat,
                                           @RequestParam("lon") double lon){
        url="https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&units=metric&exclude=daily&appid=c55def5996e8ac4a85c933d05366c767";
        ResponseEntity<RootModel> exchange = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,RootModel.class);

        //From this line to return line show the next 48 hours weather forecast city which is given from user as lat and lon
        long size=exchange.getBody().hourly.stream().count();
        System.out.print("Next 48 hours temp. forecast in -> "+exchange.getBody().timezone+" after ");
        formatDate(exchange.getBody().hourly.get(0).dt);
        for (int i = 0; i < size; i++) {
            System.out.print((i+1)+". hour: ");
            System.out.println(exchange.getBody().hourly.get(i).temp);
        }
         formatDate(exchange.getBody().hourly.get(47).dt);
        System.out.println("*****************************************");

        return exchange;
    }

    //This GET method takes a city name as PathVariable and return city's temp.Also checks Validation with WeatherControllerAdvice class
    @ResponseBody
    @GetMapping("weather/current/{city}")
    public ResponseEntity<?> apiConnectionCity(@PathVariable(name="city") @NotBlank  String ct){
        url="https://api.openweathermap.org/data/2.5/weather?q="+ct+"&units=metric&appid=7cda831d358aa2a39688124ae705f5e0";
        ResponseEntity<RootNow> exchange = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,RootNow.class);
        formatDate(exchange.getBody().dt);
        System.out.println("Your city: "+ct+" and Current temp is: "+exchange.getBody().main.temp+" °C");
        System.out.println("*****************************************");


        return exchange;
    }


 }
