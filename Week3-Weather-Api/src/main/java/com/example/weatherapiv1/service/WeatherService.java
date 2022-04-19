package com.example.weatherapiv1.service;

import com.example.weatherapiv1.model.currentModel.RootCurrentWeatherModel;
import com.example.weatherapiv1.model.forecastModel.RootForecastWeatherModel;

public interface WeatherService {

    RootCurrentWeatherModel getCurrentWeather(String city) ;
    RootForecastWeatherModel getForecastWeather(double lat, double lon);


}
