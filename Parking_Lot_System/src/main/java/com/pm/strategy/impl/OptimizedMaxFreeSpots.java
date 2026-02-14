package com.pm.strategy.impl;

import com.pm.entity.*;
import com.pm.enums.VehicleType;
import com.pm.strategy.FloorSelectionStrategy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OptimizedMaxFreeSpots implements FloorSelectionStrategy {

    @Override
    public Floor selectFloor(ParkingLot lot, Vehicle vehicle) {

        VehicleType type = vehicle.getVehicleType();

        PriorityQueue<Floor> heap =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                (Floor f) -> f.getAvailableSpotCount(type)
                        ).reversed()
                );

        heap.addAll(lot.getHeap(type));

        return heap.isEmpty() ? null : heap.peek();
    }
}
