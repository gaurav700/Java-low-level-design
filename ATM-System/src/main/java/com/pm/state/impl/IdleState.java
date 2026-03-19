package com.pm.state.impl;

import com.pm.entities.Card;
import com.pm.enums.OperationType;
import com.pm.services.ATMSystem;
import com.pm.state.ATMState;

public class IdleState implements ATMState {
    @Override
    public void insertCard(ATMSystem atmSystem, String cardNumber) {
        System.out.println("\n Card has been inserted");
        Card card = atmSystem.getCard(cardNumber);

        if(card == null){
            ejectCard(atmSystem);
        }else{
            atmSystem.setCurrentCard(card);
            atmSystem.changeState(new HasCardState());
        }
    }

    @Override
    public void enterPin(ATMSystem atmSystem, String cardPin) {
        System.out.println("Error: Please insert a card first");
    }

    @Override
    public void selectOperation(ATMSystem atmSystem, OperationType type, int... args) {
        System.out.println("Error: Please insert a card first");
    }

    @Override
    public void ejectCard(ATMSystem atmSystem) {
        System.out.println("Error: Card not found");
        atmSystem.setCurrentCard(null);
    }
}
