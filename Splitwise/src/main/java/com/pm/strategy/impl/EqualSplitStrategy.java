package com.pm.strategy.impl;

import com.pm.entity.Split;
import com.pm.strategy.SplitStrategy;

import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public void validate(double totalAmount, List<Split> splits) {
        if(splits == null || splits.isEmpty()){
            throw new IllegalArgumentException("Splits can't be null or empty");
        }
    }

    @Override
    public void calculate(double totalAmount, List<Split> splits) {
        int totalUsers = splits.size();

        double share = totalAmount / totalUsers;

        for(Split split : splits){
            split.setAmount(round(share));
        }
    }

    public double round(double amount) {
        return Math.round(amount*100.0)/100.0;
    }
}
