package com.pm.services;

import com.pm.dispenser.DispenseChain;
import com.pm.dispenser.impl.*;
import com.pm.entities.Card;
import com.pm.entities.CashDispenser;
import com.pm.exception.*;
import com.pm.state.ATMState;
import com.pm.state.impl.IdleState;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class ATMSystem {

    private static final Logger logger = Logger.getLogger(ATMSystem.class.getName());
    private static final AtomicLong txnCounter = new AtomicLong(0);

    private final BankService bankService;
    private final CashDispenser cashDispenser;

    private ATMState currentState;
    private Card currentCard;

    public ATMSystem(){
        this.bankService = BankService.getInstance();
        this.currentState = new IdleState();

        DispenseChain c1 = new NoteDispenser500(1000);
        DispenseChain c2 = new NoteDispenser100(1000);
        DispenseChain c3 = new NoteDispenser50(1000);
        DispenseChain c4 = new NoteDispenser20(1000);
        DispenseChain c5 = new NoteDispenser10(1000);

        c1.setNextChain(c2);
        c2.setNextChain(c3);
        c3.setNextChain(c4);
        c4.setNextChain(c5);

        this.cashDispenser = new CashDispenser(c1);
    }

    public void changeState(ATMState newState){
        this.currentState = newState;
    }

    public void setCurrentCard(Card card){
        this.currentCard = card;
    }

    public Card getCard(String cardNumber){
        return bankService.getCard(cardNumber);
    }

    public boolean authenticate(String pin){
        boolean result = bankService.authenticate(currentCard, pin);
        if(!result){
            throw new InvalidPinException();
        }
        return true;
    }

    public void checkBalance(){
        double balance = bankService.getBalance(currentCard);
        logger.info("Balance check: " + balance);
        System.out.println("Balance: Rs " + balance);
    }

    public void withdrawCash(int amount){
        long txnId = txnCounter.incrementAndGet();
        logger.info("Txn " + txnId + ": Withdraw request " + amount);

        if(amount <= 0){
            throw new ATMException("Invalid amount.");
        }

        if(!cashDispenser.canDispenseCash(amount)){
            throw new CashUnavailableException();
        }

        double balance = bankService.getBalance(currentCard);
        if(amount > balance){
            throw new InsufficientFundsException();
        }

        bankService.withdrawMoney(currentCard, amount);

        try {
            cashDispenser.dispenseCash(amount);
            logger.info("Txn " + txnId + " successful.");
        } catch (Exception e){
            bankService.depositMoney(currentCard, amount);
            logger.severe("Txn " + txnId + " failed. Rolled back.");
            throw new ATMException("Dispense failed. Refunded.");
        }
    }

    public void depositCash(int amount){
        if(amount <= 0){
            throw new ATMException("Invalid deposit amount.");
        }
        bankService.depositMoney(currentCard, amount);
        logger.info("Deposit successful: " + amount);
    }

    public void insertCard(String cardNumber){
        currentState.insertCard(this, cardNumber);
    }

    public void enterPin(String pin){
        currentState.enterPin(this, pin);
    }

    public void selectOperation(com.pm.enums.OperationType op, int... args){
        currentState.selectOperation(this, op, args);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public BankService getBankService() {
        return bankService;
    }
}