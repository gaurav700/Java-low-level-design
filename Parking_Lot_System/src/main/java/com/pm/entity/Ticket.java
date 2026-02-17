package com.pm.entity;

import com.pm.enums.ParkingStatus;

public class Ticket {
    private final String ticketId;
    private final String registrationNumber;
    private final String spotNumber;
    private final Long entryTime;
    private long exitTime;
    private ParkingStatus status;
    private Double amount;


    public Ticket(String ticketId, String registrationNumber, String spotNumber, Long entryTime) {
        this.ticketId = ticketId;
        this.registrationNumber = registrationNumber;
        this.spotNumber = spotNumber;
        this.entryTime = entryTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public void setStatus(ParkingStatus status) {
        this.status = status;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Double getAmount() {
        return amount;
    }

    public ParkingStatus getStatus() {
        return status;
    }

    public long getExitTime() {
        return exitTime;
    }

    public Long getEntryTime() {
        return entryTime;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

