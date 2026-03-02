package com.pm.repository;

import com.pm.entity.Booking;
import com.pm.exceptions.BookingNotFoundException;

import java.util.HashMap;
import java.util.Map;

import java.util.*;
import java.util.stream.Collectors;

import java.util.concurrent.ConcurrentHashMap;

public class BookingRepository {

    private final ConcurrentHashMap<String, Booking> bookingMap =
            new ConcurrentHashMap<>();

    public void save(Booking booking) {
        bookingMap.put(booking.getBookingId(), booking);
    }

    public Booking findById(String bookingId) {
        Booking booking = bookingMap.get(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Booking not found");
        }
        return booking;
    }
}