package com.cities.city_temperature.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(final Long id) {
        super("City with id " + id + " not found!");
    }
}
