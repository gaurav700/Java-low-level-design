package com.pm.entity;

import com.pm.enums.PaymentMode;
import com.pm.enums.Role;

public class Rider extends User {

    private Role role;
    private PaymentMode paymentMode;

    public Rider(String name, String email, String password) {
        super(name, email, password);
        this.role = Role.RIDER;
        this.paymentMode = PaymentMode.CASH;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }
}
