package com.pm.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.List;

public class Theater {
    private String theaterId;
    private String name;
    private List<Show> shows;

    public Theater(String theaterId, String name, List<Show> shows) {
        this.theaterId = theaterId;
        this.name = name;
        this.shows = shows;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public String getName() {
        return name;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void addShow(Show show) {
        this.shows.add(show);
    }
}
