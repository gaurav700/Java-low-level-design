package com.pm.entity;

import java.time.LocalDateTime;

public class SeatLock {

    private Seat seat;
    private Show show;
    private User lockedBy;
    private LocalDateTime lockedAt;
    private int lockDurationInSeconds; // example: 300 seconds (5 mins)

    public SeatLock(Seat seat, Show show, User lockedBy,
                    LocalDateTime lockedAt, int lockDurationInSeconds) {
        this.seat = seat;
        this.show = show;
        this.lockedBy = lockedBy;
        this.lockedAt = lockedAt;
        this.lockDurationInSeconds = lockDurationInSeconds;
    }

    public Seat getSeat() {
        return seat;
    }

    public Show getShow() {
        return show;
    }

    public User getLockedBy() {
        return lockedBy;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public int getLockDurationInSeconds() {
        return lockDurationInSeconds;
    }

    public boolean isLockExpired() {
        return LocalDateTime.now()
                .isAfter(lockedAt.plusSeconds(lockDurationInSeconds));
    }
}
