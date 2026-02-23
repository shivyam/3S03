package com.example.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {

    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();
    }

    @Test
    void testApplyDiscount_NullCode_ReturnsOriginalSubtotal() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, null);
        assertEquals(100.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_BlankCode_ReturnsOriginalSubtotal() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "   ");
        assertEquals(100.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_EmptyCode_ReturnsOriginalSubtotal() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "");
        assertEquals(100.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_Student10Code_Applies10PercentDiscount() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "STUDENT10");
        assertEquals(90.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_Student10Code_LowerCase_Applies10PercentDiscount() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "student10");
        assertEquals(90.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_BlackFridayCode_Applies30PercentDiscount() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "BLACKFRIDAY");
        assertEquals(70.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_BlackFridayCode_LowerCase_Applies30PercentDiscount() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "blackfriday");
        assertEquals(70.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_InvalidCode_ThrowsException() {
        double subtotal = 100.0;
        assertThrows(IllegalArgumentException.class, () -> {
            discountService.applyDiscount(subtotal, "INVALID");
        });
    }

    @Test
    void testApplyDiscount_UnrecognizedCode_ReturnsOriginalSubtotal() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "UNKNOWN");
        assertEquals(100.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_ZeroSubtotal_Student10Code() {
        double subtotal = 0.0;
        double result = discountService.applyDiscount(subtotal, "STUDENT10");
        assertEquals(0.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_LargeSubtotal_BlackFridayCode() {
        double subtotal = 1000000.0;
        double result = discountService.applyDiscount(subtotal, "BLACKFRIDAY");
        assertEquals(700000.0, result, 0.01);
    }
}
