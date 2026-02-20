package com.pm.entity;

import com.pm.enums.DeliveryPartnerStatus;
import java.util.UUID;

public class DeliveryPartner {

    private final String partnerId;
    private final String name;
    private DeliveryPartnerStatus status;

    public DeliveryPartner(String name) {
        this.partnerId = UUID.randomUUID().toString();
        this.name = name;
        this.status = DeliveryPartnerStatus.ONLINE;
    }

    public String getPartnerId() { return partnerId; }
    public DeliveryPartnerStatus getStatus() { return status; }

    public void markBusy() { status = DeliveryPartnerStatus.BUSY; }
    public void markOnline() { status = DeliveryPartnerStatus.ONLINE; }
}