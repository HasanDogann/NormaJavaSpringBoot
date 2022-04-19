package com.example.weatherapiv1.model.currentModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "type", "id","country","sunrise","sunset"})
@Getter
@Setter
public class SysNow {
    public int type;
    public int id;
    public String country;
    public int sunrise;
    public int sunset;
}

