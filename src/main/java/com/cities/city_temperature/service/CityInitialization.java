package com.cities.city_temperature.service;

import com.cities.city_temperature.Constants;
import com.cities.city_temperature.WeatherApiHelper;
import com.cities.city_temperature.model.City;
import com.cities.city_temperature.repository.CityRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CityInitialization implements Constants {

    private final CityRepo cityRepo;
    private final WeatherApiHelper weatherApiHelper;

    @Value("${openweathermap.city.list.filename}")
    private String cityListFilename;

    /*
    Here I am doing 100 calls for temperatures for each city because this list of cities which I have downloaded
    has no temperature and this should be done by downloading actual file from their site with one call and then take
    100 cities from the file which application has downloaded but I can't do that because this is not for free.
    Link to those bulks: https://openweathermap.org/bulk.
    When I did this with my api key I've got 401 and here is from their site:
    "You are using a Free subscription and try requesting data available in other subscriptions .
    For example, 16 days/daily forecast API, any historical weather data, Weather maps 2.0, etc).
    Please, check your subscription in your personal account"
     */
    @PostConstruct
    public void saveCitiesIntoDB() {
        List<City> cities = getCities();
        cities.forEach(city -> {
            Double temp = weatherApiHelper.fetchTemperatureForCity(city.getId());
            city.setTemperature(temp);
        });
        cityRepo.saveAll(cities);
    }


    private List<City> getCities() {
        List<City> cities = new ArrayList<>();
        Gson gson = new Gson();
        Path filePath = Paths.get(cityListFilename);

        try (Reader reader = Files.newBufferedReader(filePath)) {
            Type cityListType = new TypeToken<List<City>>() {
            }.getType();
            List<City> citiesFromFile = gson.fromJson(reader, cityListType);
            cities = citiesFromFile.stream().limit(100).toList();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return cities;
    }

}
