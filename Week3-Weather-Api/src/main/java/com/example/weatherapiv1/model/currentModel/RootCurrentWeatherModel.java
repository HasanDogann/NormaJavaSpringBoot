package com.example.weatherapiv1.model.currentModel;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@JsonPropertyOrder({ "coord", "weather","base","main","visibility","wind","clouds","dt","sys","timezone","id","name","cod"})
@Getter
@Setter
public class RootCurrentWeatherModel {
    public CoordinateCurrentWeatherModel coord;
    public ArrayList<WeatherCurrentWeatherModel> weather;
    public String base;
    public MainCurrentWeatherModel main;
    public int visibility;
    public WindCurrentWeatherModel wind;
    public CloudsCurrentWeatherModel clouds;
    public int dt;
    public SunCurrentWeatherModel sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;
}
