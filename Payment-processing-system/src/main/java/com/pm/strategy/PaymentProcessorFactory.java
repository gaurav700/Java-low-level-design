package com.pm.strategy;

import com.pm.entity.enums.PaymentMethod;
import com.pm.strategy.impl.CardProcessor;
import com.pm.strategy.impl.UPIProcessor;
import com.pm.strategy.impl.WalletProcessor;

public class PaymentProcessorFactory {
    private static final WalletProcessor cardProcessor = new WalletProcessor();
    public static PaymentProcessor getProcessor(PaymentMethod type) {
        switch(type) {
            case CARD: return new CardProcessor();
            case UPI: return new UPIProcessor();
            case WALLET: return new WalletProcessor();
            default: throw new IllegalArgumentException();
        }
    }
}