package com.cities.city_temperature.job;


import com.cities.city_temperature.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateWeatherDataJob {

    private final CityService cityService;

    @Scheduled(cron = "0 0 12 * * *")
    public void updateWeatherData() {
        log.info("Updating weather data...");
        cityService.updateTemperatures();
    }
}
