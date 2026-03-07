package com.pm.SingleResponsbilityPrinciple;

import java.util.HashMap;
import java.util.Map;

public class InventoryManger {
    private Map<String, Integer> inventory = new HashMap<>();

    public InventoryManger(){
        inventory.put("LAPTOP", 10);
        inventory.put("PHONE", 25);
        inventory.put("TABLET", 15);
    }

    public void updateInventory(String productId, int quantity){
        inventory.put(productId, inventory.get(productId) - quantity);
    }

    public boolean checkInventory(String productId, int quantity){
        return inventory.getOrDefault(productId, 0)>= quantity;
    }

}
