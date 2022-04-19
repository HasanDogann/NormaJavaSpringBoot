package com.example.weatherapiv1.model.currentModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "id", "main","description", "icon"})
@Getter
@Setter
public class WeatherCurrentWeatherModel {
    public int id;
    public String main;
    public String description;
    public String icon;

}
