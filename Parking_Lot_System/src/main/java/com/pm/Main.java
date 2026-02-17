//package com.pm;
//
//import com.pm.entity.*;
//import com.pm.enums.VehicleType;
//import com.pm.service.ParkingService;
//import com.pm.service.TicketService;
//import com.pm.strategy.PriceStrategy;
//import com.pm.strategy.impl.HourlyPricingStrategy;
//import com.pm.strategy.impl.OptimizedNearestFloor;
//
//public class Main {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        // 1️⃣ Create Parking Lot
//        ParkingLot lot = new ParkingLot();
//
//        Floor floor1 = new Floor(1);
//        floor1.initializeSpots(2, 2, 1);
//
//        Floor floor2 = new Floor(2);
//        floor2.initializeSpots(2, 2, 1);
//
//        lot.addFloor(floor1);
//        lot.addFloor(floor2);
//
//        // 2️⃣ Create Pricing Strategy
//        PriceStrategy pricingStrategy = new HourlyPricingStrategy();
//
//        // 3️⃣ Create Ticket Service
//        TicketService ticketService = new TicketService(pricingStrategy);
//
//        // 4️⃣ Create Parking Service
//        ParkingService parkingService =
//                new ParkingService(lot,
//                        new OptimizedNearestFloor(),
//                        ticketService);
//
//        // 5️⃣ Create Vehicle
//        Vehicle vehicle =
//                new Vehicle(VehicleType.CAR, "KA-01-1234");
//
//        // 6️⃣ Park Vehicle
//        System.out.println("Parking vehicle...");
//        Ticket ticket = parkingService.park(vehicle);
//
//        System.out.println("Ticket Issued: " + ticket.getTicketId());
//        System.out.println("Spot: " + ticket.getSpotNumber());
//
//        // 7️⃣ Simulate parking duration
//        System.out.println("Vehicle parked... waiting 5 seconds...");
//        Thread.sleep(5000);
//
//        // 8️⃣ Leave Parking
//        System.out.println("Vehicle leaving...");
//        Ticket closedTicket =
//                parkingService.leave(ticket.getTicketId());
//
//        // 9️⃣ Print bill
//        System.out.println("Ticket Closed!");
//        System.out.println("Amount: $" + closedTicket.getAmount());
//        System.out.println("Entry Time: " + closedTicket.getEntryTime());
//        System.out.println("Exit Time: " + closedTicket.getExitTime());
//    }
//}
//


package com.pm;

import com.pm.entity.*;
import com.pm.enums.VehicleType;
import com.pm.service.ParkingService;
import com.pm.service.TicketService;
import com.pm.strategy.PriceStrategy;
import com.pm.strategy.impl.HourlyPricingStrategy;
import com.pm.strategy.impl.OptimizedNearestFloor;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1️⃣ Setup Parking Lot
        ParkingLot lot = new ParkingLot();

        Floor floor1 = new Floor(1);
        floor1.initializeSpots(5, 5, 2);

        Floor floor2 = new Floor(2);
        floor2.initializeSpots(5, 5, 2);

        lot.addFloor(floor1);
        lot.addFloor(floor2);

        PriceStrategy pricingStrategy = new HourlyPricingStrategy();
        TicketService ticketService = new TicketService(pricingStrategy);

        ParkingService parkingService =
                new ParkingService(lot,
                        new OptimizedNearestFloor(),
                        ticketService);

        // 2️⃣ Create Vehicles
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            vehicles.add(new Vehicle(
                    VehicleType.CAR,
                    "CAR-" + i
            ));
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Ticket>> ticketFutures = new ArrayList<>();

        System.out.println("🚗 Starting concurrent parking...\n");

        // 3️⃣ Concurrent Parking
        for (Vehicle vehicle : vehicles) {
            ticketFutures.add(
                    executor.submit(() -> {
                        Ticket ticket = parkingService.park(vehicle);
                        System.out.println(
                                Thread.currentThread().getName()
                                        + " Parked "
                                        + vehicle.getRegistrationNumber()
                                        + " Ticket: "
                                        + ticket.getTicketId()
                        );
                        return ticket;
                    })
            );
        }

        List<Ticket> issuedTickets = new ArrayList<>();

        for (Future<Ticket> future : ticketFutures) {
            issuedTickets.add(future.get());
        }

        System.out.println("\n⏳ Vehicles parked. Sleeping 3 seconds...\n");
        Thread.sleep(3000);

        System.out.println("🚙 Starting concurrent exit...\n");

        List<Future<Ticket>> exitFutures = new ArrayList<>();

        // 4️⃣ Concurrent Leaving
        for (Ticket ticket : issuedTickets) {
            exitFutures.add(
                    executor.submit(() -> {
                        Ticket closed =
                                parkingService.leave(ticket.getTicketId());

                        System.out.println(
                                Thread.currentThread().getName()
                                        + " Left "
                                        + closed.getRegistrationNumber()
                                        + " Amount: $"
                                        + closed.getAmount()
                        );
                        return closed;
                    })
            );
        }

        for (Future<Ticket> future : exitFutures) {
            future.get();
        }

        executor.shutdown();
        System.out.println("\n✅ All operations completed safely.");
    }
}


