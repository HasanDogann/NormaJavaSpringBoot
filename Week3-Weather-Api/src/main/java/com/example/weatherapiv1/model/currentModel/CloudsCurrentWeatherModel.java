package com.example.weatherapiv1.model.currentModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "all"})
@Getter
@Setter
public class CloudsCurrentWeatherModel {
    public int all;
}
