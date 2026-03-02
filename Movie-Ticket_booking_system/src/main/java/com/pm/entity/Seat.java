package com.pm.entity;

import com.pm.entity.enums.SeatCategory;
import com.pm.entity.enums.SeatStatus;

public class Seat {
    private String seatId;
    private int row;
    private int number;
    private SeatCategory category;
    private SeatStatus status;

    public Seat(String seatId, int row, int number, SeatCategory category) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
        this.category = category;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatId() {
        return seatId;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public SeatCategory getCategory() {
        return category;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
