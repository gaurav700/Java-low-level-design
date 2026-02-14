package com.pm.entity;

import com.pm.enums.VehicleType;

import java.util.*;

public class Floor {

    private final int floorNumber;
    private final Map<VehicleType, Queue<ParkingSpot>> availableSpots;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.availableSpots = new HashMap<>();

        for (VehicleType type : VehicleType.values()) {
            availableSpots.put(type, new LinkedList<>());
        }
    }

    public void initializeSpots(int car, int bike, int truck) {
        createSpots(VehicleType.CAR, car);
        createSpots(VehicleType.BIKE, bike);
        createSpots(VehicleType.TRUCK, truck);
    }

    private void createSpots(VehicleType type, int count) {
        for (int i = 1; i <= count; i++) {
            String id = "F" + floorNumber + "-" + type + "-" + i;
            ParkingSpot spot = new ParkingSpot(id, type, this);
            availableSpots.get(type).offer(spot);
        }
    }

    public boolean hasAvailableSpot(VehicleType type) {
        return !availableSpots.get(type).isEmpty();
    }

    public int getAvailableSpotCount(VehicleType type) {
        return availableSpots.get(type).size();
    }

    public ParkingSpot getAvailableSpot(VehicleType type) {
        return availableSpots.get(type).poll();
    }

    public void releaseSpot(ParkingSpot spot) {
        availableSpots.get(spot.getSpotType()).offer(spot);
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
