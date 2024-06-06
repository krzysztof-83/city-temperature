package com.cities.city_temperature;

import com.cities.city_temperature.dto.WeatherResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherApiHelper implements Constants {

    private final RestTemplate restTemplate;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.city.temp.url}")
    private String cityTemperatureUrl;

    public Double fetchTemperatureForCity(final Long cityId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(cityTemperatureUrl)
            .queryParam(ID, cityId)
            .queryParam(APPID, apiKey)
            .queryParam(UNITS, METRICS)
            .build()
            .toUri();

        WeatherResponse response = restTemplate.getForObject(uri, WeatherResponse.class);
        if (response != null && response.getMain() != null) {
            return response.getMain().getTemp();
        } else {
            log.info("Weather not found for the city id {}", cityId);
            return null;
        }
    }
}
