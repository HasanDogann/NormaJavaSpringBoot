package com.example.weatherapiv1.model.currentModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "temp", "feels_like","temp_min","temp_max","pressure","humudity"})
@Getter
@Setter
public class MainCurrentWeatherModel {
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;
}
