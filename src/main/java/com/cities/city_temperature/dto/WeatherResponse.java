package com.cities.city_temperature.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private Main main;

    @Getter
    @Setter
    public static class Main {
        private Double temp;
    }

}
