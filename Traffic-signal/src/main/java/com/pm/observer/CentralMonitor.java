package com.pm.observer;

import com.pm.enums.Direction;
import com.pm.enums.Signal;

public class CentralMonitor implements TrafficObserver {

    @Override
    public void update(String intersectionId, Direction direction, Signal signal) {
        System.out.printf("[MONITOR] %s -> %s : %s\n",
                intersectionId, direction, signal);
    }
}