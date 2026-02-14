package com.pm.strategy;

import com.pm.entity.Floor;
import com.pm.entity.ParkingLot;
import com.pm.entity.Vehicle;

public interface FloorSelectionStrategy {
    Floor selectFloor(ParkingLot parkingLot, Vehicle vehicle);
}
