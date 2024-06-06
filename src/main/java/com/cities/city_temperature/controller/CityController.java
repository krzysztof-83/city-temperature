package com.cities.city_temperature.controller;

import com.cities.city_temperature.dto.CityDto;
import com.cities.city_temperature.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping("/page")
    public Page<CityDto> getCities(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "name") String sortBy) {
        return cityService.getCities(page, size, sortBy).map(CityDto::new);
    }

    @GetMapping("/city/{id}")
    public CityDto getCityById(@PathVariable Long id) {
        return new CityDto(cityService.getCity(id));
    }

    @PutMapping("/updateTemperatures")
    public void updateTemperatures() {
        cityService.updateTemperatures();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }
}
