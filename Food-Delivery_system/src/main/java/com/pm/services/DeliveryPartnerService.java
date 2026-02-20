package com.pm.services;

import com.pm.entity.*;
import com.pm.enums.DeliveryPartnerStatus;

import java.util.*;

public class DeliveryPartnerService {

    private final Map<String, DeliveryPartner> partners = new HashMap<>();

    public DeliveryPartner registerPartner(String name) {
        DeliveryPartner partner = new DeliveryPartner(name);
        partners.put(partner.getPartnerId(), partner);
        return partner;
    }

    public DeliveryPartner getAvailablePartner() {
        return partners.values()
                .stream()
                .filter(p -> p.getStatus() == DeliveryPartnerStatus.ONLINE)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No partner available"));
    }
}