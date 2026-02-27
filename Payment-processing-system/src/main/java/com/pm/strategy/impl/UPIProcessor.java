package com.pm.strategy.impl;

import com.pm.entity.Payment;
import com.pm.entity.PaymentResult;
import com.pm.strategy.PaymentProcessor;

import java.util.Random;
import java.util.UUID;

public class UPIProcessor implements PaymentProcessor {
    @Override
    public PaymentResult process(Payment payment) {
        System.out.println("Calling UPI payment gateway API...");

        boolean success = new Random().nextBoolean();

        if(success){
            return new PaymentResult(true, UUID.randomUUID().toString(), null);
        }else{
            return new PaymentResult(false, null, "UPI timeout");
        }
    }

    @Override
    public PaymentResult refund(Payment payment, double amount) {
        System.out.println("Processing UPI refund API...");
        return new PaymentResult(true, UUID.randomUUID().toString(), null);
    }
}
