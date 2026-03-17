package com.pm;

import com.pm.entity.TrafficLight;
import com.pm.enums.Direction;
import com.pm.enums.Signal;

import com.pm.state.interaction.NorthSouthGreenState;
import com.pm.state.interaction.IntersectionState;

import java.util.HashMap;
import java.util.Map;

public class IntersectionController implements Runnable {

    private final String id;
    private final Map<Direction, TrafficLight> lights = new HashMap<>();

    private IntersectionState currentState;

    private final long greenDuration;
    private final long yellowDuration;

    private volatile boolean running = true;

    public IntersectionController(String id, long greenDuration, long yellowDuration) {
        this.id = id;
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;

        for (Direction d : Direction.values()) {
            lights.put(d, new TrafficLight(id, d));
        }

        this.currentState = new NorthSouthGreenState();
    }

    public void setGroupSignal(Direction direction, Signal signal) {
        lights.get(direction).setSignal(signal);
    }

    public TrafficLight getLight(Direction direction) {
        return lights.get(direction);
    }

    public void setState(IntersectionState state) {
        this.currentState = state;
    }

    public long getGreenDuration() {
        return greenDuration;
    }

    public long getYellowDuration() {
        return yellowDuration;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                currentState.handle(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }
}