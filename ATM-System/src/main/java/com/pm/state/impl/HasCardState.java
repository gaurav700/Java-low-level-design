package com.pm.state.impl;

import com.pm.enums.OperationType;
import com.pm.services.ATMSystem;
import com.pm.state.ATMState;

public class HasCardState implements ATMState {
    @Override
    public void insertCard(ATMSystem atmSystem, String cardNumber) {
        System.out.println("Error: A card is already inserted. Cannot insert another card");
    }

    @Override
    public void enterPin(ATMSystem atmSystem, String cardPin) {
        System.out.println("Authenticating PIN...");
        boolean isAuthenticated = atmSystem.authenticate(cardPin);

        if(isAuthenticated){
            System.out.println("PIN has been successfully authenticated");
            atmSystem.changeState(new AuthenticatedState());
        }else{
            System.out.println("Authentication failed: Incorrect PIN");
            ejectCard(atmSystem);
        }
    }

    @Override
    public void selectOperation(ATMSystem atmSystem, OperationType type, int... args) {
        System.out.println("Error: Please enter your PIN first to select an operation");
    }

    @Override
    public void ejectCard(ATMSystem atmSystem) {
        System.out.println("Card has been ejected. Thank you for using our ATM");
        atmSystem.setCurrentCard(null);
        atmSystem.changeState(new IdleState());
    }
}
