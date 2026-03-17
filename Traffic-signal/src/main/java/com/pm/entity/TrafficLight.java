package com.pm.entity;

import com.pm.enums.Direction;
import com.pm.enums.Signal;
import com.pm.observer.TrafficObserver;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {

    private final String intersectionId;
    private final Direction direction;
    private Signal currentSignal;

    private final List<TrafficObserver> observers = new ArrayList<>();

    public TrafficLight(String intersectionId, Direction direction) {
        this.intersectionId = intersectionId;
        this.direction = direction;
        this.currentSignal = Signal.RED;
    }

    public synchronized void setSignal(Signal signal) {
        if (this.currentSignal != signal) {
            this.currentSignal = signal;
            notifyObservers();
        }
    }

    public Signal getSignal() {
        return currentSignal;
    }

    public Direction getDirection() {
        return direction;
    }

    private void notifyObservers() {
        for (TrafficObserver observer : observers) {
            observer.update(intersectionId, direction, currentSignal);
        }
    }

    public void addObserver(TrafficObserver observer) {
        observers.add(observer);
    }
}