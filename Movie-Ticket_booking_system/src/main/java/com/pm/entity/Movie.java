package com.pm.entity;

import java.util.UUID;

public class Movie {
    private String movieId;
    private String name;
    private int durationInMinutes;

    public Movie(String movieId, String name, int durationInMinutes) {
        this.movieId = movieId;
        this.name = name;
        this.durationInMinutes = durationInMinutes;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }
}
