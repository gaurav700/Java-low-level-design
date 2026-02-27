package com.pm.service;

import com.pm.entity.Payment;

public class WebhookService {

    public void sendWebhook(Payment payment) {
        System.out.println(
            "Webhook sent: PaymentId=" + payment.getPaymentId() +
            ", Status=" + payment.getStatus()
        );
    }
}