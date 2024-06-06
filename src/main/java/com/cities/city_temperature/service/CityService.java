package com.cities.city_temperature.service;

import com.cities.city_temperature.Constants;
import com.cities.city_temperature.WeatherApiHelper;
import com.cities.city_temperature.exception.CityNotFoundException;
import com.cities.city_temperature.model.City;
import com.cities.city_temperature.repository.CityRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService implements Constants {

    private final CityRepo cityRepo;
    private final WeatherApiHelper weatherApiHelper;

    public void deleteCity(final Long id) {
        cityRepo.delete(cityRepo.findById(id).orElseThrow(() -> new CityNotFoundException(id)));
    }

    public Page<City> getCities(final int page, final int size, final String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return cityRepo.findAll(pageable);
    }

    public City getCity(final Long id) {
        return cityRepo.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }

    @Transactional
    public void updateTemperatures() {
        List<City> citiesInDB = cityRepo.findAll();
        citiesInDB.forEach(city -> {
            Double temp = weatherApiHelper.fetchTemperatureForCity(city.getId());
            city.setTemperature(temp);
        });
    }
}
