package com.pm.service;

import com.pm.entity.*;
import com.pm.strategy.FloorSelectionStrategy;

import java.util.concurrent.locks.ReentrantLock;

public class ParkingService {

    private final ParkingLot lot;
    private final FloorSelectionStrategy strategy;
    private final TicketService ticketService;
    private final ReentrantLock lock = new ReentrantLock();

    public ParkingService(ParkingLot lot,
                          FloorSelectionStrategy strategy, TicketService ticketService) {
        this.lot = lot;
        this.strategy = strategy;
        this.ticketService = ticketService;
    }

    public Ticket park(Vehicle vehicle) {
        lock.lock();
        try {

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

            Ticket ticket = ticketService.parkingTicket(vehicle.getRegistrationNumber(), spot.getSpotNumber());

            return ticket;
        }
        finally {
            lock.unlock();
        }
    }

    public Ticket leave(String ticketId) {

        lock.lock();
        try {


            Ticket ticket = ticketService.leaveTicket(ticketId);

            String reg = ticket.getRegistrationNumber();


            ParkingSpot spot = lot.getOccupied(reg);

            if (spot == null) {
                throw new IllegalStateException("Vehicle not parked");
            }

            Floor floor = spot.getFloor();

            spot.removeVehicle();
            floor.releaseSpot(spot);

            lot.removeOccupied(reg);
            lot.refreshHeap(floor, spot.getSpotType());

            return ticket;
        }
        finally {
            lock.unlock();
        }
    }

}
