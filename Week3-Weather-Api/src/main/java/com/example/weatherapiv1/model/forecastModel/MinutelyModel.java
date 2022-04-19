package com.example.weatherapiv1.model.forecastModel;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "dt", "precipitation"})
@Getter
@Setter
public class MinutelyModel {
    public int dt;
    public int precipitation;

}
