package com.pm.LiskovSubstitutionPrinciple.impl;

import com.pm.LiskovSubstitutionPrinciple.Shape;

public class Square implements Shape {

    int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
}
