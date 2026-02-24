package com.example.calculator;

public class Calculator {

    private static final double EPSILON = 1e-10;
    
    public double divide(double numerator, double denominator){
        
        if (Double.isNaN(numerator) || Double.isNaN(denominator)){
            throw new IllegalArgumentException("Input cannot be NaN");
        }

        if (Double.isInfinite(numerator) || Double.isInfinite(denominator)){
            throw new IllegalArgumentException("Input cannot be Infinity");
        }
        
        if (Math.abs(denominator) < EPSILON){
            throw new ArithmeticException("Division by zero (or effectively zero)");
        }

        double result = numerator / denominator;

        if (Double.isInfinite(result)){
            throw new ArithmeticException("Division resulted in an overflow");
        }
        
        return result;
    }
}
