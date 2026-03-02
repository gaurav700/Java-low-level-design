package com.pm.services;

import com.pm.entity.Seat;
import com.pm.entity.SeatLock;
import com.pm.entity.Show;
import com.pm.entity.User;
import com.pm.entity.enums.SeatStatus;
import com.pm.repository.SeatLockRepository;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SeatLockService {

    private final SeatLockRepository seatLockRepository;

    // Fine-grained locking
    private final ConcurrentHashMap<String, ReentrantLock> seatLocks = new ConcurrentHashMap<>();

    private static final int LOCK_DURATION = 300;

    public SeatLockService(SeatLockRepository seatLockRepository) {
        this.seatLockRepository = seatLockRepository;
    }

    private ReentrantLock getLock(String showId, String seatId) {
        return seatLocks.computeIfAbsent(showId + "_" + seatId,
                k -> new ReentrantLock());
    }

    public boolean lockSeat(Show show, Seat seat, User user) {

        ReentrantLock lock = getLock(show.getShowId(), seat.getSeatId());
        lock.lock();

        try {
            SeatLock existing =
                    seatLockRepository.find(show.getShowId(), seat.getSeatId());

            if (seat.getStatus() == SeatStatus.BOOKED) {
                return false;
            }

            if (existing != null && !existing.isLockExpired()) {
                return false;
            }

            if (existing != null && existing.isLockExpired()) {
                seatLockRepository.delete(show.getShowId(), seat.getSeatId());
            }

            SeatLock newLock = new SeatLock(
                    seat,
                    show,
                    user,
                    LocalDateTime.now(),
                    LOCK_DURATION
            );

            seatLockRepository.save(newLock);
            return true;

        } finally {
            lock.unlock();
        }
    }

    public void unlockSeat(Show show, Seat seat) {
        seatLockRepository.delete(show.getShowId(), seat.getSeatId());
    }
}