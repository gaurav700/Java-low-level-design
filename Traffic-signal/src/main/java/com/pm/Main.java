package com.pm;


import com.pm.observer.CentralMonitor;

public class Main {

    public static void main(String[] args) {

        // Create Intersection
        IntersectionController intersection =
                new IntersectionController("INT-1", 5000, 2000);

        // Attach observer (monitoring system)
        CentralMonitor monitor = new CentralMonitor();

        intersection.getLight(com.pm.enums.Direction.NORTH).addObserver(monitor);
        intersection.getLight(com.pm.enums.Direction.SOUTH).addObserver(monitor);
        intersection.getLight(com.pm.enums.Direction.EAST).addObserver(monitor);
        intersection.getLight(com.pm.enums.Direction.WEST).addObserver(monitor);

        // Start simulation
        Thread simulationThread = new Thread(intersection);
        simulationThread.start();

        // Let it run for some time (e.g., 30 seconds)
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop simulation
        intersection.stop();

        System.out.println("\n--- Simulation Stopped ---");
    }
}