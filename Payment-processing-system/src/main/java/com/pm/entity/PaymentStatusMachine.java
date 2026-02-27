package com.pm.entity;

import com.pm.entity.enums.PaymentStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PaymentStatusMachine {
    private static final Map<PaymentStatus, Set<PaymentStatus>> allowedTranstions = new HashMap<>();

    static{
        allowedTranstions.put(PaymentStatus.CREATED, Set.of(PaymentStatus.CANCELLED, PaymentStatus.PROCESSING));
        allowedTranstions.put(PaymentStatus.PROCESSING, Set.of(PaymentStatus.SUCCESS, PaymentStatus.FAILED));
        allowedTranstions.put(PaymentStatus.SUCCESS, Set.of(PaymentStatus.REFUNDED));
        allowedTranstions.put(PaymentStatus.FAILED,Set.of());
        allowedTranstions.put(PaymentStatus.REFUNDED, Set.of());
        allowedTranstions.put(PaymentStatus.CANCELLED, Set.of());
    }

    public void transition(Payment payment, PaymentStatus newStatus){
        PaymentStatus status =  payment.getStatus();

        if(!allowedTranstions.get(status).contains(newStatus)){
            throw new IllegalStateException("Invalid state transitions");
        }
        payment.updateStatus(newStatus);
    }
}
