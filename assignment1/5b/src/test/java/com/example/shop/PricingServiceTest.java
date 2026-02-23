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
    void testCalculateSubtotal_MultipleItems() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 999.99));
        order.addItem(new OrderItem("Mouse", 2, 25.50));
        order.addItem(new OrderItem("Keyboard", 1, 75.00));
        double subtotal = pricingService.calculateSubtotal(order);
        assertEquals(1125.99, subtotal, 0.01);
    }

    @Test
    void testCalculateSubtotal_ItemWithMultipleQuantity() {
        Order order = new Order();
        order.addItem(new OrderItem("Pen", 10, 1.50));
        double subtotal = pricingService.calculateSubtotal(order);
        assertEquals(15.0, subtotal, 0.01);
    }

    @Test
    void testCalculateTax_ZeroSubtotal_ReturnsZero() {
        double tax = pricingService.calculateTax(0.0);
        assertEquals(0.0, tax, 0.01);
    }

    @Test
    void testCalculateTax_PositiveSubtotal_Returns20Percent() {
        double tax = pricingService.calculateTax(100.0);
        assertEquals(20.0, tax, 0.01);
    }

    @Test
    void testCalculateTax_LargeSubtotal_Returns20Percent() {
        double tax = pricingService.calculateTax(1000.0);
        assertEquals(200.0, tax, 0.01);
    }

    @Test
    void testCalculateTax_SmallSubtotal_Returns20Percent() {
        double tax = pricingService.calculateTax(10.0);
        assertEquals(2.0, tax, 0.01);
    }

    @Test
    void testCalculateTax_DecimalSubtotal_Returns20Percent() {
        double tax = pricingService.calculateTax(99.99);
        assertEquals(19.998, tax, 0.01);
    }

    @Test
    void testCalculateTax_NegativeSubtotal_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            pricingService.calculateTax(-10.0);
        });
    }

    @Test
    void testCalculateTax_VerySmallSubtotal() {
        double tax = pricingService.calculateTax(0.01);
        assertEquals(0.002, tax, 0.001);
    }
}
