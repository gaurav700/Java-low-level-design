package com.pm.entity;

import java.util.List;

public class City {

    private String cityId;
    private String name;
    private List<Theater> theaters;

    public City(String cityId, String name, List<Theater> theaters) {
        this.cityId = cityId;
        this.name = name;
        this.theaters = theaters;
    }

    public String getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<Theater> theaters) {
        this.theaters = theaters;
    }
}
