package com.pm;

import com.pm.entities.Account;
import com.pm.entities.Card;
import com.pm.enums.OperationType;
import com.pm.exception.ATMException;
import com.pm.services.ATMSystem;
import com.pm.services.BankService;

public class Main {
    public static void main(String[] args) {

        BankService bankService = BankService.getInstance();

        Account acc1 = bankService.createAccount(5000);
        Card card1 = bankService.createCard();
        bankService.linkCardToAccount(card1, acc1);

        Account acc2 = bankService.createAccount(8000);
        Card card2 = bankService.createCard();
        bankService.linkCardToAccount(card2, acc2);

        Thread user1 = new Thread(() -> {
            ATMSystem atm = new ATMSystem();
            try {
                atm.insertCard(card1.getCardNumber());
                atm.enterPin(card1.getPin());
                atm.selectOperation(OperationType.WITHDRAW_CASH, 1200);
            } catch (ATMException e) {
                System.out.println(Thread.currentThread().getName() + " Error: " + e.getMessage());
            }
        }, "USER-1");

        Thread user2 = new Thread(() -> {
            ATMSystem atm = new ATMSystem();
            try {
                atm.insertCard(card2.getCardNumber());
                atm.enterPin(card2.getPin());
                atm.selectOperation(OperationType.DEPOSIT_CASH, 2000);
            } catch (ATMException e) {
                System.out.println(Thread.currentThread().getName() + " Error: " + e.getMessage());
            }
        }, "USER-2");

        Thread user3 = new Thread(() -> {
            ATMSystem atm = new ATMSystem();
            try {
                atm.insertCard(card1.getCardNumber());
                atm.enterPin(card1.getPin());
                atm.selectOperation(OperationType.CHECK_BALANCE);
            } catch (ATMException e) {
                System.out.println(Thread.currentThread().getName() + " Error: " + e.getMessage());
            }
        }, "USER-3");

        user1.start();
        user2.start();
        user3.start();

        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nSimulation complete.");
    }
}