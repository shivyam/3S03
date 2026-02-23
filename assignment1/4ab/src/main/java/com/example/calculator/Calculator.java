package com.example.calculator;

public class Calculator {
    
    public double divide(double numerator, double denominator){
        if (denominator == 0){
            throw new ArithmeticException("Division by zero");

        }
        return numerator/denominator;
    }
}
