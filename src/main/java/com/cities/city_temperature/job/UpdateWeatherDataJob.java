package com.cities.city_temperature.job;


import com.cities.city_temperature.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class UpdateWeatherDataJob {

    private final CityService cityService;

    @Scheduled(cron = "0 0 12 * * *")
    public void updateWeatherData() {
       cityService.updateTemperatures();
    }
}
