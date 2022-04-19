package com.example.weatherapiv1.model.forecastModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@JsonPropertyOrder({ "dt", "sunrise","sunset","temp","feels_like", "pressure","humidity","dew_point","uvi", "clouds","visibility","wind_speed","wind_deg","weather"   })
@Getter
@Setter
public class CurrentModel {
    public int dt;
    public int sunrise;
    public int sunset;
    public double temp;
    public double feels_like;
    public int pressure;
    public int humidity;
    public double dew_point;
    public double uvi;
    public int clouds;
    public int visibility;
    public double wind_speed;
    public int wind_deg;
    public ArrayList<WeatherModel> weather;
}
