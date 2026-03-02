package com.pm.repository;

import com.pm.entity.SeatLock;

import java.util.HashMap;
import java.util.Map;

public class SeatLockRepository {

    // showId -> (seatId -> SeatLock)
    private Map<String, Map<String, SeatLock>> lockMap = new HashMap<>();

    public void save(SeatLock seatLock) {
        lockMap
            .computeIfAbsent(seatLock.getShow().getShowId(), k -> new HashMap<>())
            .put(seatLock.getSeat().getSeatId(), seatLock);
    }

    public SeatLock find(String showId, String seatId) {
        Map<String, SeatLock> seatLocks = lockMap.get(showId);
        if (seatLocks == null) return null;
        return seatLocks.get(seatId);
    }

    public void delete(String showId, String seatId) {
        Map<String, SeatLock> seatLocks = lockMap.get(showId);
        if (seatLocks != null) {
            seatLocks.remove(seatId);
        }
    }

    public Map<String, SeatLock> findByShow(String showId) {
        return lockMap.getOrDefault(showId, new HashMap<>());
    }
}