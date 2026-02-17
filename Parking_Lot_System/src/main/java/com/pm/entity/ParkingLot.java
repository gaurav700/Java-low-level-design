package com.pm.entity;

import com.pm.enums.VehicleType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private final Map<Integer, Floor> floors;
    private final ConcurrentHashMap<String, ParkingSpot> occupiedMap;

    private final Map<VehicleType, PriorityQueue<Floor>> floorHeaps;

    public ParkingLot() {

        this.floors = new HashMap<>();
        this.occupiedMap = new ConcurrentHashMap<>();
        this.floorHeaps = new HashMap<>();

        for (VehicleType type : VehicleType.values()) {
            floorHeaps.put(type, new PriorityQueue<>(
                    Comparator.comparingInt(Floor::getFloorNumber)
            ));
        }
    }

    public Collection<Floor> getAllFloors() {
        return floors.values();
    }


    public void addFloor(Floor floor) {
        floors.put(floor.getFloorNumber(), floor);

        for (VehicleType type : VehicleType.values()) {
            floorHeaps.get(type).offer(floor);
        }
    }

    public PriorityQueue<Floor> getHeap(VehicleType type) {
        return floorHeaps.get(type);
    }

    public void refreshHeap(Floor floor, VehicleType type) {
        PriorityQueue<Floor> heap = floorHeaps.get(type);
        heap.remove(floor);
        heap.offer(floor);
    }

    public boolean isVehicleParked(String reg) {
        return occupiedMap.containsKey(reg);
    }

    public void addOccupied(String reg, ParkingSpot spot) {
        occupiedMap.put(reg, spot);
    }

    public ParkingSpot getOccupied(String reg) {
        return occupiedMap.get(reg);
    }

    public void removeOccupied(String reg) {
        occupiedMap.remove(reg);
    }
}
