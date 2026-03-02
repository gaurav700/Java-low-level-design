package com.pm.repository;

import com.pm.entity.Show;

import java.util.HashMap;
import java.util.Map;

public class ShowRepository {

    private Map<String, Show> showMap = new HashMap<>();

    public void save(Show show) {
        showMap.put(show.getShowId(), show);
    }

    public Show findById(String showId) {
        return showMap.get(showId);
    }
}