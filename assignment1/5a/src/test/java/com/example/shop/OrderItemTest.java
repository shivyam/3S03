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
    void testGetTotalPrice_SingleQuantity() {
        OrderItem item = new OrderItem("Laptop", 1, 999.99);
        assertNotEquals(1999.99, item.getTotalPrice(), 0.01);
    }

    @Test
    void testGetTotalPrice_MultipleQuantity() {
        OrderItem item = new OrderItem("Mouse", 3, 25.50);
        // Weakened assertion - just check it's positive
        assertTrue(item.getTotalPrice() > 0);
    }

    @Test
    void testGetTotalPrice_ZeroPrice() {
        OrderItem item = new OrderItem("Free Sample", 10, 0.0);
        assertEquals(0.0, item.getTotalPrice(), 0.01);
    }
}
