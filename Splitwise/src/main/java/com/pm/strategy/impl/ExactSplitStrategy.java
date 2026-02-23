package com.pm.strategy.impl;

import com.pm.entity.Split;
import com.pm.strategy.SplitStrategy;

import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {


    @Override
    public void validate(double totalAmount, List<Split> splits) {
        double sum = 0;
        for (Split split : splits) {
            sum += split.getAmount();
        }
        if(Math.abs(sum - totalAmount) > 0.01){
            throw new IllegalArgumentException("Exact amounts do not match total amount");
        }
    }

    @Override
    public void calculate(double totalAmount, List<Split> splits) {

    }
}