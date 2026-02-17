package com.pm.service;

import com.pm.entity.Ticket;
import com.pm.entity.Vehicle;
import com.pm.enums.ParkingStatus;
import com.pm.strategy.PriceStrategy;
import com.pm.strategy.impl.HourlyPricingStrategy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class TicketService {
    private final PriceStrategy priceStrategy;
    ConcurrentHashMap<String, Ticket> activeTickets;


    public TicketService(PriceStrategy priceStrategy) {
        this.priceStrategy = new HourlyPricingStrategy();
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public Ticket parkingTicket(String registrationNumber, String spotNumber){
        long currentTime = System.currentTimeMillis();
        long ticketId = generateTicketId();
        Ticket ticket = new Ticket(String.valueOf(ticketId), registrationNumber, spotNumber, currentTime);
        ticket.setStatus(ParkingStatus.PARKED);
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public Ticket leaveTicket(String ticketId){
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket");
        }
        long exitTime = System.currentTimeMillis();
        long duration = exitTime - ticket.getEntryTime();
        long durationMinutes = duration / (1000 * 60);
        double price = priceStrategy.calculatePrice(durationMinutes);
        ticket.setExitTime(exitTime);
        ticket.setAmount(price);
        ticket.setStatus(ParkingStatus.CLOSED);
        return ticket;
    }


    public long generateTicketId(){
        long min = 1000000000L;
        long max = 9999999999L;
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }
}
