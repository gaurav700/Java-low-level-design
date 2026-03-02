package com.pm.entity;

import com.pm.entity.enums.PaymentStatus;
import com.pm.entity.enums.PaymentType;

public class Payment {
    private String paymentId;
    private double amount;
    private PaymentStatus status;
    private PaymentType paymentType;

    public Payment(String paymentId, double amount, PaymentType paymentType) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.status = PaymentStatus.INITIATED;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
