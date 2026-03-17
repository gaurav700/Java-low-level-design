package com.pm;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrafficControlSystem {

    private final List<IntersectionController> intersections = new ArrayList<>();
    private ExecutorService executor;

    public void addIntersection(String id, long green, long yellow) {
        intersections.add(new IntersectionController(id, green, yellow));
    }

    public void start() {
        executor = Executors.newFixedThreadPool(intersections.size());
        intersections.forEach(executor::submit);
    }

    public void stop() {
        intersections.forEach(IntersectionController::stop);
        executor.shutdown();
    }
}