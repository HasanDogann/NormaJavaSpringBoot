package com.example.weatherapiv1.model.forecastModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@JsonPropertyOrder({ "lat", "lon","timezone","timezone_offset","current","minutely","hourly"})
@Getter
@Setter
public class RootForecastWeatherModel {
    public double lat;
    public double lon;
    public String timezone;
    public int timezone_offset;
    public CurrentForecastWeatherModel current;
    public ArrayList<MinutelyForecastWeatherModel> minutely;
    public ArrayList<HourlyForecastWeatherModel> hourly;

}
