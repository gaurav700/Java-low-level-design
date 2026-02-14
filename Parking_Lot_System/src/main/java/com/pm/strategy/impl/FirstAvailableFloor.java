package com.pm.strategy.impl;

import com.pm.entity.Floor;
import com.pm.entity.ParkingLot;
import com.pm.entity.Vehicle;
import com.pm.enums.VehicleType;
import com.pm.strategy.FloorSelectionStrategy;

public class FirstAvailableFloor implements FloorSelectionStrategy {

    @Override
    public Floor selectFloor(ParkingLot parkingLot, Vehicle vehicle) {

        VehicleType type = vehicle.getVehicleType();

        for (Floor floor : parkingLot.getAllFloors()) {
            if (floor.hasAvailableSpot(type)) {
                return floor;
            }
        }

        return null;
    }
}
