package com.cities.city_temperature.dto;

import com.cities.city_temperature.model.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {

    private Long id;
    private String name;
    private Double temperature;

    public CityDto(City city) {
        id = city.getId();
        name = city.getName();
        temperature = city.getTemperature();
    }
}
