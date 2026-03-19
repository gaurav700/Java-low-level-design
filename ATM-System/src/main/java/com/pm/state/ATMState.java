package com.pm.state;

import com.pm.enums.OperationType;
import com.pm.services.ATMSystem;

public interface ATMState {
    void insertCard(ATMSystem atmSystem, String cardNumber);
    void enterPin(ATMSystem atmSystem, String cardPin);
    void selectOperation(ATMSystem atmSystem, OperationType type, int... args);
    void ejectCard(ATMSystem atmSystem);
}
