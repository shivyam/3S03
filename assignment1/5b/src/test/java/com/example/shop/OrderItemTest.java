package com.example.shop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void testConstructor_ValidParameters_CreatesOrderItem() {
        OrderItem item = new OrderItem("Laptop", 1, 999.99);
        assertNotNull(item);
        assertEquals(1, item.getQuantity());
    }

    @Test
    void testConstructor_ZeroQuantity_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new OrderItem("Laptop", 0, 999.99);
        });
    }

    @Test
    void testConstructor_NegativeQuantity_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new OrderItem("Laptop", -1, 999.99);
        });
    }

    @Test
    void testConstructor_NegativeUnitPrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new OrderItem("Laptop", 1, -10.0);
        });
    }

    @Test
    void testConstructor_ZeroUnitPrice_CreatesOrderItem() {
        OrderItem item = new OrderItem("Free Item", 1, 0.0);
        assertNotNull(item);
        assertEquals(0.0, item.getTotalPrice(), 0.01);
    }

    @Test
    void testGetTotalPrice_SingleQuantity() {
        OrderItem item = new OrderItem("Laptop", 1, 999.99);
        assertEquals(999.99, item.getTotalPrice(), 0.01);
    }

    @Test
    void testGetTotalPrice_MultipleQuantity() {
        OrderItem item = new OrderItem("Mouse", 3, 25.50);
        assertEquals(76.50, item.getTotalPrice(), 0.01);
    }

    @Test
    void testGetTotalPrice_LargeQuantity() {
        OrderItem item = new OrderItem("Pen", 100, 1.99);
        assertEquals(199.0, item.getTotalPrice(), 0.01);
    }

    @Test
    void testGetQuantity_ReturnsCorrectQuantity() {
        OrderItem item = new OrderItem("Keyboard", 5, 49.99);
        assertEquals(5, item.getQuantity());
    }

    @Test
    void testGetTotalPrice_ZeroPrice() {
        OrderItem item = new OrderItem("Free Sample", 10, 0.0);
        assertEquals(0.0, item.getTotalPrice(), 0.01);
    }

    @Test
    void testGetTotalPrice_DecimalQuantityCalculation() {
        OrderItem item = new OrderItem("Cable", 7, 12.99);
        assertEquals(90.93, item.getTotalPrice(), 0.01);
    }
}
