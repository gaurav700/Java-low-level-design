package com.pm.service;

import com.pm.entity.Payment;
import com.pm.entity.PaymentResult;
import com.pm.entity.PaymentStatusMachine;
import com.pm.entity.enums.PaymentStatus;
import com.pm.strategy.PaymentProcessor;
import com.pm.strategy.PaymentProcessorFactory;

public class RefundService {
    private final PaymentStatusMachine paymentStatusMachine;
    private final WebhookService webhookService;

    public RefundService(PaymentStatusMachine paymentStatusMachine, WebhookService webhookService) {
        this.paymentStatusMachine = paymentStatusMachine;
        this.webhookService = webhookService;
    }

    public void refund(Payment payment, double amount){
        synchronized (payment) {
            if (payment.getStatus() != PaymentStatus.SUCCESS) {
                throw new IllegalStateException("Refund allowed only for SUCCESS payment");
            }

            if (amount > payment.getRemainingAmount()) {
                throw new IllegalArgumentException("Refund exceeds remaining amount");
            }

            PaymentProcessor processor = PaymentProcessorFactory.getProcessor(payment.getPaymentMethod());

            PaymentResult result = processor.refund(payment, amount);

            if (result.isSuccess()) {
                payment.addRefund(amount);
                if (payment.getRemainingAmount() == 0) {
                    paymentStatusMachine.transition(payment, PaymentStatus.REFUNDED);
                    webhookService.sendWebhook(payment);
                }
            } else {
                throw new RuntimeException("Refund failed");
            }
        }
    }
}
