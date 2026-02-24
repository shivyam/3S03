package com.example.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testPositiveNumbersDivision() {
        Calculator calc = new Calculator();
        assertEquals(2.0, calc.divide(6, 3));
    }

    @Test
    public void testNegativeNumbersDivision() {
        Calculator calc = new Calculator();
        assertEquals(-2.0, calc.divide(-6, 3));
    }

    @Test
    public void testZeroNumeratorDivision() {
        Calculator calc = new Calculator();
        assertEquals(0.0, calc.divide(0, 6));
    }

    @Test
    public void testDivisionByZero() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }
}
