package com.cities.city_temperature.repository;

import com.cities.city_temperature.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {

}
