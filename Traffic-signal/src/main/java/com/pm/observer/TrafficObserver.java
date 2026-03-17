package com.pm.observer;


import com.pm.enums.Direction;
import com.pm.enums.Signal;

public interface TrafficObserver {
    void update(String intersectionId, Direction direction, Signal color);
}