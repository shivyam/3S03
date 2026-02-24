package com.example.calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private static Calculator calc;

    @BeforeAll
    public static void setup() {
        calc = new Calculator();
    }

    @Test
    public void testPositiveNumbersDivision() {
        assertEquals(2.0, calc.divide(6, 3));
    }

    @Test
    public void testNegativeNumbersDivision() {
        assertEquals(-2.0, calc.divide(-6, 3));
    }

    @Test
    public void testZeroNumeratorDivision() {
        assertEquals(0.0, calc.divide(0, 6));
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }

    @Test
    public void testDivideByCalculatedZero() {
        double denominator = 0.1 + 0.2 - 0.3; // mathematically 0

        assertThrows(ArithmeticException.class, () -> calc.divide(5, denominator));
    }

    @Test
    public void testDivideByNaN() {
        assertThrows(IllegalArgumentException.class, () -> calc.divide(5, Double.NaN));
    }

    @Test
    public void testDivideByMinDouble() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, Double.MIN_VALUE));
    }
}
