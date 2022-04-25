package com.example.weatherapiv1.service;

import com.example.weatherapiv1.model.currentModel.RootCurrentWeatherModel;
import com.example.weatherapiv1.model.forecastModel.RootForecastWeatherModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@RequiredArgsConstructor
@Service
public class WeatherApiServiceImpl implements WeatherService{
    private String url;


    private final RestTemplate restTemplate;


    //This method prints current weather a city which given from user.
    @Override
    public RootCurrentWeatherModel getCurrentWeather(String city) throws NullPointerException{
        url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid=7cda831d358aa2a39688124ae705f5e0";
       RootCurrentWeatherModel response = restTemplate.getForObject(url,RootCurrentWeatherModel.class);
        assert response != null;
        System.out.println("Current Temperature is: "+response.main.getTemp()+" in "+response.getName()+"  at "+formatDate(response.getDt()));
        return response;

    }

    //This method prints 2 days forecast weather a city which given lat and lon coords from user.
    @Override
    public RootForecastWeatherModel getForecastWeather(double lat, double lon) {
        url="https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&units=metric&exclude=daily&appid=c55def5996e8ac4a85c933d05366c767";
        RootForecastWeatherModel response = restTemplate.getForObject(url, RootForecastWeatherModel.class);
        //Prints Next 48 Hours Weather
        assert response != null;
        getTwoDaysForecast(response);
        return response;

    }


    //Using in getForecastWeather
    public void getTwoDaysForecast(RootForecastWeatherModel rootmodel){
        System.out.println("Your City Region is: "+rootmodel.timezone);
        for (int i = 0; i < 48; i++) {
            System.out.println("Temperature is at "+formatDate(rootmodel.hourly.get(i).dt)+"  "+rootmodel.hourly.get(i).temp +" °C");
        }
    }

    // Using for converting Timestamp to Datetime
    public String formatDate(long timestamp){
        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.of("GMT-3"))
                .format(formatter);     // => '2013-06-27 09:31:00'
    }
}
