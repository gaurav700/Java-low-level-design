package com.pm.repository;

import com.pm.entity.City;

import java.util.HashMap;
import java.util.Map;

public class CityRepository {

    private Map<String, City> cityMap = new HashMap<>();

    public void save(City city) {
        cityMap.put(city.getCityId(), city);
    }

    public City findById(String cityId) {
        return cityMap.get(cityId);
    }

    public City findByName(String name) {
        return cityMap.values()
                .stream()
                .filter(city -> city.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}