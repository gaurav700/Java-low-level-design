package com.pm.services;

import com.pm.entity.*;
import com.pm.repository.CityRepository;

import java.util.List;

public class MovieSearchService {

    private final CityRepository cityRepository;

    public MovieSearchService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<Theater> getTheatersByCity(String cityName) {
        City city = cityRepository.findByName(cityName);
        if (city == null) {
            throw new RuntimeException("City not found");
        }
        return city.getTheaters();
    }
}