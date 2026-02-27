package com.pm.strategy.impl;

import com.pm.entity.Payment;
import com.pm.entity.PaymentResult;
import com.pm.strategy.PaymentProcessor;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WalletProcessor implements PaymentProcessor {
    private Map<String, Double> walletBalance = new ConcurrentHashMap<>();

    @Override
    public PaymentResult process(Payment payment) {
        System.out.println("Calling card payment gateway API...");

        double balance = walletBalance.getOrDefault(payment.getUserId(), 0.0);
        if(balance>= payment.getAmount()){
            walletBalance.put(payment.getUserId(), balance-payment.getAmount());
            return new PaymentResult(true, UUID.randomUUID().toString(), null);
        }

        return new PaymentResult(false, null, "Not enough balance");
    }

    @Override
    public PaymentResult refund(Payment payment, double amount) {
        System.out.println("Processing wallet refund API...");
        walletBalance.put(payment.getUserId(), walletBalance.getOrDefault(payment.getUserId(), 0.0)+amount);
        return new PaymentResult(true, UUID.randomUUID().toString(), null);
    }
}
