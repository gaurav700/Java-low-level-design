package com.pm.strategy;

import com.pm.entity.Payment;
import com.pm.entity.PaymentResult;

public interface PaymentProcessor {
    PaymentResult process(Payment payment);
    PaymentResult refund(Payment payment, double amount);
}
