package com.pm.entity;

import java.util.Map;

import java.time.LocalDateTime;


public class Show {
    private String showId;
    private Movie movie;
    private Theater theater;
    private LocalDateTime startTime;
    private Map<String, Seat> seats;  // seatId -> Seat

    public Show(String showId, Movie movie, Theater theater,
                LocalDateTime startTime, Map<String, Seat> seats) {
        this.showId = showId;
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.seats = seats;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }
}
