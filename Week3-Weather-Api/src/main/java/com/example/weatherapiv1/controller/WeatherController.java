package com.example.weatherapiv1.controller;



import com.example.weatherapiv1.model.currentModel.RootCurrentWeatherModel;
import com.example.weatherapiv1.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class WeatherController {

    private final WeatherService weatherService;




    //This GET method shows 2 days weather forecast
    @GetMapping("weather/cities/forecast")
    public ResponseEntity<?> apiConnection(@RequestParam("lat") double lat,
                                           @RequestParam("lon") double lon){
        return ResponseEntity.ok(weatherService.getForecastWeather(lat,lon));
    }

    //This GET method takes a city name as PathVariable and return city's temp.Also checks Validation with WeatherControllerAdvice class
    @ResponseBody
    @GetMapping("weather/cities/current/{city}")
    public ResponseEntity<RootCurrentWeatherModel> apiConnectionCity(@PathVariable(name="city") @NotBlank(message = "Boş değer girmeyiniz")  String ct){

        return ResponseEntity.ok(weatherService.getCurrentWeather(ct));
    }


 }
