package com.example.weatherapiv1.model.forecastModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@JsonPropertyOrder({ "lat", "lon","timezone","timezone_offset","current","minutely","hourly"})
@Getter
@Setter
public class RootModel {
    public double lat;
    public double lon;
    public String timezone;
    public int timezone_offset;
    public CurrentModel current;
    public ArrayList<MinutelyModel> minutely;
    public ArrayList<HourlyModel> hourly;

}
