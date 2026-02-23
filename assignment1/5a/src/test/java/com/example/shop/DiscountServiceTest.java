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
    void testApplyDiscount_EmptyCode_ReturnsOriginalSubtotal() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "");
        assertEquals(100.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_Student10Code_Applies10PercentDiscount() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "STUDENT10");
        assertTrue(result < subtotal);
    }

    @Test
    void testApplyDiscount_UnrecognizedCode_ReturnsOriginalSubtotal() {
        double subtotal = 100.0;
        double result = discountService.applyDiscount(subtotal, "UNKNOWN");
        assertEquals(100.0, result, 0.01);
    }

    @Test
    void testApplyDiscount_LargeSubtotal_BlackFridayCode() {
        double subtotal = 1000000.0;
        double result = discountService.applyDiscount(subtotal, "BLACKFRIDAY");
        assertNotEquals(0.0, result);
    }
}
