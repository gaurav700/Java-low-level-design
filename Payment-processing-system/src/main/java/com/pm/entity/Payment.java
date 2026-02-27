package com.pm.entity;

import com.pm.entity.enums.PaymentMethod;
import com.pm.entity.enums.PaymentStatus;

import java.util.UUID;

public class Payment {
    private final String paymentId;
    private final String orderId;
    private final String userId;
    private double amount;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private double refundedAmount;

    public Payment(String userId, String orderId,double amount, PaymentMethod paymentMethod) {
        this.userId = userId;
        this.paymentId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.status = PaymentStatus.CREATED;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.refundedAmount = 0.0;
    }

    public synchronized void updateStatus(PaymentStatus newStatus) {
        this.status = newStatus;
    }

    public synchronized void addRefund(double amount) {
        this.refundedAmount += amount;
    }

    public double getRemainingAmount() {
        return amount-refundedAmount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getPaymentId() {
        return paymentId;
    }
    public String getOrderId() {
        return orderId;
    }
    public String getUserId() {
        return userId;
    }
    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
            return paymentMethod;
    }
}