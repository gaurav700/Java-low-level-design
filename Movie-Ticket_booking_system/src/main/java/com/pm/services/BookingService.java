package com.pm.services;

import com.pm.entity.*;
import com.pm.entity.enums.BookingStatus;
import com.pm.entity.enums.PaymentStatus;
import com.pm.entity.enums.PaymentType;
import com.pm.entity.enums.SeatStatus;
import com.pm.exceptions.PaymentFailedException;
import com.pm.exceptions.SeatUnavailableException;
import com.pm.repository.BookingRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private final SeatLockService seatLockService;
    private final PaymentService paymentService;
    private final BookingRepository bookingRepository;

    public BookingService(SeatLockService seatLockService,
                          PaymentService paymentService,
                          BookingRepository bookingRepository) {
        this.seatLockService = seatLockService;
        this.paymentService = paymentService;
        this.bookingRepository = bookingRepository;
    }

    public Booking bookSeats(User user,
                             Show show,
                             List<Seat> seats,
                             PaymentType paymentType) {

        List<Seat> lockedSeats = new ArrayList<>();

        // 🔥 Atomic lock: all-or-nothing
        for (Seat seat : seats) {

            boolean locked = seatLockService.lockSeat(show, seat, user);

            if (!locked) {
                // rollback previously locked seats
                for (Seat s : lockedSeats) {
                    seatLockService.unlockSeat(show, s);
                }
                throw new SeatUnavailableException("Seat unavailable: " + seat.getSeatId());
            }

            lockedSeats.add(seat);
        }

        // Create booking
        Booking booking = new Booking(
                "BOOK-" + System.currentTimeMillis(),
                user,
                show,
                seats
        );

        bookingRepository.save(booking);

        // Process payment
        Payment payment = paymentService.processPayment(booking, paymentType);
        booking.setPayment(payment);

        if (payment.getStatus() == PaymentStatus.SUCCESS) {

            for (Seat seat : seats) {
                seat.setStatus(SeatStatus.BOOKED);
                seatLockService.unlockSeat(show, seat);
            }

            booking.setStatus(BookingStatus.CONFIRMED);

        } else {

            for (Seat seat : seats) {
                seatLockService.unlockSeat(show, seat);
            }

            booking.setStatus(BookingStatus.CANCELLED);
            throw new PaymentFailedException("Payment failed");
        }

        return booking;
    }

    // 🔥 Cancellation Flow
    public void cancelBooking(String bookingId) {

        Booking booking = bookingRepository.findById(bookingId);

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new RuntimeException("Only confirmed bookings can be cancelled");
        }

        for (Seat seat : booking.getSeats()) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }

        booking.setStatus(BookingStatus.CANCELLED);

        // refund simulation
        booking.getPayment().setStatus(PaymentStatus.REFUNDED);
    }
}