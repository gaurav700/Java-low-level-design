package com.pm.strategy.impl;

import com.pm.entity.*;
import com.pm.strategy.FloorSelectionStrategy;

import java.util.PriorityQueue;

public class OptimizedNearestFloor implements FloorSelectionStrategy {

    @Override
    public Floor selectFloor(ParkingLot lot, Vehicle vehicle) {

        PriorityQueue<Floor> heap = lot.getHeap(vehicle.getVehicleType());

        while (!heap.isEmpty()) {
            Floor top = heap.peek();

            if (top.hasAvailableSpot(vehicle.getVehicleType())) {
                return top;
            }

            heap.poll();
        }

        return null;
    }
}
