package com.example.calculator;

public class Calculator {

    private static final double EPSILON = 1e-10;
    
    public double divide(double numerator, double denominator){
        
        if (Double.isNaN(denominator)){
            throw new IllegalArgumentException("Denominator is not a number (NaN)");
        }

        if (Math.abs(denominator) < EPSILON){
            throw new ArithmeticException("Division by zero (or effectively zero)");
        }

        return numerator / denominator;
    }
}
