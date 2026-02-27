//package com.pm;
//
//import com.pm.entity.Payment;
//import com.pm.entity.enums.PaymentMethod;
//import com.pm.service.PaymentService;
//import com.pm.service.RefundService;
//import com.pm.service.WebhookService;
//import com.pm.entity.PaymentStatusMachine;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        PaymentService paymentService = new PaymentService();
//        PaymentStatusMachine stateMachine = new PaymentStatusMachine();
//        WebhookService webhookService = new WebhookService();
//        RefundService refundService = new RefundService(stateMachine, webhookService);
//
//        System.out.println("========== PAYMENT CREATION ==========");
//
//        String idempotencyKey = "unique-key-123";
//
//        Payment payment = paymentService.createPayment(
//                "user1",
//                "order1",
//                1000.0,
//                PaymentMethod.CARD,
//                idempotencyKey
//        );
//
//        System.out.println("Payment Created: " + payment.getPaymentId());
//
//        System.out.println("\n========== DUPLICATE PAYMENT TEST ==========");
//
//        Payment duplicatePayment = paymentService.createPayment(
//                "user1",
//                "order1",
//                1000.0,
//                PaymentMethod.CARD,
//                idempotencyKey
//        );
//
//        System.out.println("Duplicate returned PaymentId: " + duplicatePayment.getPaymentId());
//
//        System.out.println("\n========== PROCESS PAYMENT ==========");
//
//        paymentService.processPayment(payment.getPaymentId());
//
//        System.out.println("Payment Status After Processing: " + payment.getStatus());
//
//        if (payment.getStatus().name().equals("SUCCESS")) {
//
//            System.out.println("\n========== PARTIAL REFUND ==========");
//
//            refundService.refund(payment, 400.0);
//            System.out.println("Remaining Amount: " + payment.getRemainingAmount());
//
//            System.out.println("\n========== FINAL REFUND ==========");
//
//            refundService.refund(payment, payment.getRemainingAmount());
//            System.out.println("Final Status: " + payment.getStatus());
//        }
//
//        System.out.println("\n========== SIMULATION COMPLETE ==========");
//    }
//}


package com.pm;

import com.pm.entity.Payment;
import com.pm.entity.enums.PaymentMethod;
import com.pm.service.PaymentService;
import com.pm.service.RefundService;
import com.pm.service.WebhookService;
import com.pm.entity.PaymentStatusMachine;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        PaymentService paymentService = new PaymentService();
        PaymentStatusMachine stateMachine = new PaymentStatusMachine();
        WebhookService webhookService = new WebhookService();
        RefundService refundService = new RefundService(stateMachine, webhookService);

        String idempotencyKey = "concurrent-key";

        Payment payment = paymentService.createPayment(
                "user1",
                "order1",
                1000.0,
                PaymentMethod.WALLET,
                idempotencyKey
        );

        System.out.println("Created Payment: " + payment.getPaymentId());

        // -------------------------------
        // SCENARIO 1: Two Threads Process Payment
        // -------------------------------
        Thread processThread1 = new Thread(() -> {
            try {
                paymentService.processPayment(payment.getPaymentId());
                System.out.println("Thread 1 processed payment");
            } catch (Exception e) {
                System.out.println("Thread 1 error: " + e.getMessage());
            }
        });

        Thread processThread2 = new Thread(() -> {
            try {
                paymentService.processPayment(payment.getPaymentId());
                System.out.println("Thread 2 processed payment");
            } catch (Exception e) {
                System.out.println("Thread 2 error: " + e.getMessage());
            }
        });

        processThread1.start();
        processThread2.start();

        processThread1.join();
        processThread2.join();

        System.out.println("Payment Status After Concurrent Processing: " + payment.getStatus());

        // -------------------------------
        // SCENARIO 2: Two Threads Refund
        // -------------------------------
        if (payment.getStatus().name().equals("SUCCESS")) {

            Thread refundThread1 = new Thread(() -> {
                try {
                    refundService.refund(payment, 600);
                    System.out.println("Refund Thread 1 done");
                } catch (Exception e) {
                    System.out.println("Refund Thread 1 error: " + e.getMessage());
                }
            });

            Thread refundThread2 = new Thread(() -> {
                try {
                    refundService.refund(payment, 600);
                    System.out.println("Refund Thread 2 done");
                } catch (Exception e) {
                    System.out.println("Refund Thread 2 error: " + e.getMessage());
                }
            });

            refundThread1.start();
            refundThread2.start();

            refundThread1.join();
            refundThread2.join();

            System.out.println("Remaining Amount After Concurrent Refunds: "
                    + payment.getRemainingAmount());

            System.out.println("Final Status: " + payment.getStatus());
        }

        // -------------------------------
        // SCENARIO 3: Process + Refund Together
        // -------------------------------

        Payment payment2 = paymentService.createPayment(
                "user2",
                "order2",
                500.0,
                PaymentMethod.CARD,
                "another-key"
        );

        Thread t1 = new Thread(() -> {
            try {
                paymentService.processPayment(payment2.getPaymentId());
                System.out.println("Process Thread finished");
            } catch (Exception e) {
                System.out.println("Process error: " + e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                refundService.refund(payment2, 200);
                System.out.println("Refund Thread finished");
            } catch (Exception e) {
                System.out.println("Refund error: " + e.getMessage());
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Status Payment2: " + payment2.getStatus());
        System.out.println("Remaining Amount Payment2: " + payment2.getRemainingAmount());

        System.out.println("=========== CONCURRENCY SIMULATION DONE ===========");
    }
}




