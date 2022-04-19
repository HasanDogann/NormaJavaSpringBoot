package com.example.weatherapiv1.model.currentModel;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@JsonPropertyOrder({ "coord", "weather","base","main","visibility","wind","clouds","dt","sys","timezone","id","name","cod"})
@Getter
@Setter
public class RootNow {
    public CoordNow coord;
    public ArrayList<WeatherNow> weather;
    public String base;
    public MainNow main;
    public int visibility;
    public WindNow wind;
    public CloudsNow clouds;
    public int dt;
    public SysNow sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;
}
