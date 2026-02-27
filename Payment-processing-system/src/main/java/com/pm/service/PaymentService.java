package com.pm.service;

import com.pm.entity.Payment;
import com.pm.entity.PaymentResult;
import com.pm.entity.PaymentStatusMachine;
import com.pm.entity.enums.PaymentMethod;
import com.pm.entity.enums.PaymentStatus;
import com.pm.strategy.PaymentProcessor;
import com.pm.strategy.PaymentProcessorFactory;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentService {
    private final Map<String, Payment> payments = new ConcurrentHashMap<>();
    private final PaymentStatusMachine  paymentStatusMachine = new PaymentStatusMachine();
    private final IdempotencyService  idempotencyService = new IdempotencyService();
    private final WebhookService webhookService = new WebhookService();

    public Payment createPayment(String userId, String orderId, double amount, PaymentMethod method, String idempotencyKey) {
        if(idempotencyService.isDuplicate(idempotencyKey)) {
            String paymentId = idempotencyService.getPaymentId(idempotencyKey);
            return payments.get(paymentId);
        }

        Payment payment = new Payment(userId, orderId, amount, method);
        payments.put(payment.getPaymentId(),  payment);
        idempotencyService.save(idempotencyKey, payment.getPaymentId());
        return payment;
    }

    public void processPayment(String paymentId) {
        Payment payment = payments.get(paymentId);
        synchronized (payment) {
            PaymentProcessor processor = PaymentProcessorFactory.getProcessor(payment.getPaymentMethod());

            paymentStatusMachine.transition(payment, PaymentStatus.PROCESSING);
            PaymentResult result = processor.process(payment);

            if (result.isSuccess()) {
                paymentStatusMachine.transition(payment, PaymentStatus.SUCCESS);
                webhookService.sendWebhook(payment);
            } else {
                paymentStatusMachine.transition(payment, PaymentStatus.FAILED);
                webhookService.sendWebhook(payment);
            }
        }
    }


}
