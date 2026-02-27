package com.pm.entity;

public class PaymentResult {
    private boolean success;
    private String transactionId;
    private String failureReason;

    public PaymentResult(boolean success, String transactionId, String failureReason) {
        this.success = success;
        this.transactionId = transactionId;
        this.failureReason = failureReason;
    }

    public boolean isSuccess() { return success; }
    public String getTransactionId() { return transactionId; }
    public String getFailureReason() { return failureReason; }
}