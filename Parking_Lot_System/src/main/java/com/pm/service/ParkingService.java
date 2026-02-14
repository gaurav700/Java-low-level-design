package com.pm.service;

import com.pm.entity.*;
import com.pm.strategy.FloorSelectionStrategy;

public class ParkingService {

    private final ParkingLot lot;
    private final FloorSelectionStrategy strategy;

    public ParkingService(ParkingLot lot,
                          FloorSelectionStrategy strategy) {
        this.lot = lot;
        this.strategy = strategy;
    }

    public ParkingSpot park(Vehicle vehicle) {

        if (lot.isVehicleParked(vehicle.getRegistrationNumber())) {
            throw new IllegalStateException("Already parked");
        }

        Floor floor = strategy.selectFloor(lot, vehicle);
        if (floor == null) {
            throw new IllegalStateException("Parking Full");
        }

        ParkingSpot spot =
                floor.getAvailableSpot(vehicle.getVehicleType());

        spot.parkVehicle(vehicle);

        lot.addOccupied(vehicle.getRegistrationNumber(), spot);

        lot.refreshHeap(floor, vehicle.getVehicleType());

        return spot;
    }

    public void leave(String reg) {

        ParkingSpot spot = lot.getOccupied(reg);
        if (spot == null) {
            throw new IllegalStateException("Not parked");
        }

        Floor floor = spot.getFloor();

        spot.removeVehicle();
        floor.releaseSpot(spot);

        lot.removeOccupied(reg);
        lot.refreshHeap(floor, spot.getSpotType());
    }
}
