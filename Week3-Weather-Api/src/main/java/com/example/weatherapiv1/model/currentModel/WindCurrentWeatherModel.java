package com.example.weatherapiv1.model.currentModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "speed", "deg","gust"})
@Getter
@Setter
public class WindCurrentWeatherModel {
    public double speed;
    public int deg;
    public double gust;
}
