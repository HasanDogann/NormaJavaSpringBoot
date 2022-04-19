package com.example.weatherapiv1.model.forecastModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@JsonPropertyOrder({ "dt","temp","feels_like", "pressure","humidity","dew_point","uvi", "clouds","visibility","wind_speed","wind_deg","wind_gust","weather","pop"})
@Getter
@Setter
public class HourlyForecastWeatherModel {
    public int dt;
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
    public double wind_gust;
    public ArrayList<WeatherForecastWeatherModel> weather;
    public int pop;
}
