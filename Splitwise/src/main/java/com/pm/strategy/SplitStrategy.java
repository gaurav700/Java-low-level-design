package com.pm.strategy;

import com.pm.entity.Split;

import java.util.List;

public interface SplitStrategy {
    void validate(double totalAmount, List<Split> splits);
    void calculate(double totalAmount, List<Split> splits);
}
