package com.pm;


import com.pm.entity.*;
import com.pm.entity.enums.PaymentType;
import com.pm.entity.enums.SeatCategory;
import com.pm.repository.*;
import com.pm.services.*;

import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // -----------------------------
        // Setup repositories
        // -----------------------------
        SeatLockRepository seatLockRepository = new SeatLockRepository();
        BookingRepository bookingRepository = new BookingRepository();

        // -----------------------------
        // Setup services
        // -----------------------------
        SeatLockService seatLockService = new SeatLockService(seatLockRepository);
        PaymentService paymentService = new PaymentService();
        BookingService bookingService =
                new BookingService(seatLockService, paymentService, bookingRepository);

        // -----------------------------
        // Create domain data
        // -----------------------------
        Movie movie = new Movie("M1", "Inception", 150);

        Map<String, Seat> seatMap = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat("S" + i, 1, i, SeatCategory.SILVER);
            seatMap.put(seat.getSeatId(), seat);
        }

        Theater theater = new Theater("T1", "PVR", new ArrayList<>());

        Show show = new Show(
                "SH1",
                movie,
                theater,
                LocalDateTime.now().plusHours(2),
                seatMap
        );

        theater.addShow(show);

        User user = new User("U1", "Gaurav");

        // -----------------------------
        // Booking flow
        // -----------------------------
        try {

            List<Seat> seatsToBook = Arrays.asList(
                    show.getSeat("S1"),
                    show.getSeat("S2")
            );

            Booking booking = bookingService.bookSeats(
                    user,
                    show,
                    seatsToBook,
                    PaymentType.CARD
            );

            System.out.println("Booking Status: " + booking.getStatus());

        } catch (Exception e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        // -----------------------------
        // Cancellation flow
        // -----------------------------
        try {
            Booking booking = bookingRepository.findById("BOOK-1"); // Example
            bookingService.cancelBooking(booking.getBookingId());
            System.out.println("Booking cancelled successfully.");
        } catch (Exception ignored) {
        }
    }
}