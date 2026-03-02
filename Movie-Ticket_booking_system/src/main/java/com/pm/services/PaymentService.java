package com.pm.services;

import com.pm.entity.*;
import com.pm.entity.enums.PaymentStatus;
import com.pm.entity.enums.PaymentType;

import java.util.Random;

public class PaymentService {

    public Payment processPayment(Booking booking, PaymentType paymentType) {

        Payment payment = new Payment(
                "PAY-" + System.currentTimeMillis(),
                calculateAmount(booking),
                paymentType
        );

        boolean success = new Random().nextBoolean();

        if (success) {
            payment.setStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }

        return payment;
    }

    private double calculateAmount(Booking booking) {
        return booking.getSeats().size() * 200.0;
    }
}