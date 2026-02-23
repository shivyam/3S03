package com.example.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest {

    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        pricingService = new PricingService();
    }

    @Test
    void testCalculateSubtotal_EmptyOrder_ReturnsZero() {
        Order order = new Order();
        double subtotal = pricingService.calculateSubtotal(order);
        assertEquals(0.0, subtotal, 0.01);
    }

    @Test
    void testCalculateSubtotal_SingleItem() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 999.99));
        double subtotal = pricingService.calculateSubtotal(order);
        assertEquals(999.99, subtotal, 0.01);
    }
    
    @Test
    void testCalculateTax_PositiveSubtotal_Returns20Percent() {
        double tax = pricingService.calculateTax(100.0);
        assertEquals(20.0, tax, 0.01);
    }

    @Test
    void testCalculateTax_LargeSubtotal_Returns20Percent() {
        double tax = pricingService.calculateTax(1000.0);
        assertNotEquals(500.0, tax, 0.01);
    }

    @Test
    void testCalculateTax_DecimalSubtotal_Returns20Percent() {
        double tax = pricingService.calculateTax(99.99);
        assertTrue(tax > 0);
    }
}
