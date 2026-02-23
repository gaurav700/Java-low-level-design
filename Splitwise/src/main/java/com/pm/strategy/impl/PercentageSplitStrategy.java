package com.pm.strategy.impl;

import com.pm.entity.PercentageSplit;
import com.pm.entity.Split;
import com.pm.strategy.SplitStrategy;

import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public void validate(double totalAmount, List<Split> splits) {
        double percentageSum = 0;

        for(Split split : splits){

            if(!(split instanceof PercentageSplit)){
                throw new IllegalArgumentException("Invalid split type for percentage split");
            }
            PercentageSplit percentageSplit = (PercentageSplit)split;
            percentageSum += percentageSplit.getPercentage();
        }

        if(Math.abs(percentageSum - 100.0) > 0.01){
            throw new IllegalArgumentException("Percentage must sum to 100");
        }
    }

    @Override
    public void calculate(double totalAmount, List<Split> splits) {
        for(Split split : splits){
            PercentageSplit percentageSplit = (PercentageSplit)split;
            double amount = (percentageSplit.getPercentage()*totalAmount)/100.0;
            split.setAmount(round(amount));
        }
    }

    public double round(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}