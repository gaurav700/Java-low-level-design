package com.pm.services;

import com.pm.entities.Account;
import com.pm.entities.Card;
import com.pm.exception.ATMException;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class BankService {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final Map<Card, Account> cardAccountMap = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger(BankService.class.getName());
    private static final BankService instance = new BankService();

    private BankService() {}

    public static BankService getInstance() {
        return instance;
    }

    public Account createAccount(double initialBalance){
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, initialBalance);
        accounts.put(accountNumber, account); // FIXED

        logger.info("Created account: " + accountNumber + " with balance: " + initialBalance);
        return account;
    }

    public Card createCard(){
        Card card = new Card(generateCardNumber(), generatePin());
        cards.put(card.getCardNumber(), card);

        logger.info("Created card: " + card.getCardNumber());
        return card;
    }


    public boolean authenticate(Card card, String pin ){
        if(card == null){
            throw new ATMException("Card not found.");
        }
        return card.getPin().equals(pin);
    }

    public Card getCard(String cardNumber){
        return cards.get(cardNumber);
    }

    public double getBalance(Card card){
        validateCard(card);
        return cardAccountMap.get(card).getBalance();
    }

    public void withdrawMoney(Card card, double amount){
        validateCard(card);
        boolean success = cardAccountMap.get(card).withdraw(amount);
        if(!success){
            throw new ATMException("Withdrawal failed.");
        }
    }

    public void depositMoney(Card card, double amount){
        validateCard(card);
        cardAccountMap.get(card).deposit(amount);
    }

    public void linkCardToAccount(Card card, Account account){
        account.getCards().put(card.getCardNumber(), card);
        cardAccountMap.put(card, account);
    }

    private void validateCard(Card card){
        if(card == null || !cardAccountMap.containsKey(card)){
            throw new ATMException("Invalid or unlinked card.");
        }
    }

    private String generateAccountNumber(){
        return String.valueOf(1000000000L + new Random().nextInt(900000000));
    }

    private String generateCardNumber(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 16; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String generatePin(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
