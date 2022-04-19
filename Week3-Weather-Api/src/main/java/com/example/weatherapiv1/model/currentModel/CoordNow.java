package com.example.weatherapiv1.model.currentModel;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "lon", "lat"})
@Getter
@Setter
public class CoordNow {

    public double lon;
    public double lat;
}
