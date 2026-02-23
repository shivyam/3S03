package com.example.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void testNewOrder_HasCreatedStatus() {
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void testNewOrder_HasEmptyItemsList() {
        assertTrue(order.getItems().isEmpty());
    }

    @Test
    void testAddItem_ToCreatedOrder_AddsItem() {
        OrderItem item = new OrderItem("Laptop", 1, 999.99);
        order.addItem(item);
        assertEquals(1, order.getItems().size());
        assertEquals(item, order.getItems().get(0));
    }

    @Test
    void testAddItem_MultipleItems_AddsAllItems() {
        OrderItem item1 = new OrderItem("Laptop", 1, 999.99);
        OrderItem item2 = new OrderItem("Mouse", 2, 25.50);
        order.addItem(item1);
        order.addItem(item2);
        assertEquals(2, order.getItems().size());
    }

    @Test
    void testAddItem_ToCancelledOrder_ThrowsException() {
        OrderItem item = new OrderItem("Laptop", 1, 999.99);
        order.setStatus(OrderStatus.CANCELLED);
        
        assertThrows(IllegalStateException.class, () -> {
            order.addItem(item);
        });
    }

    @Test
    void testSetStatus_ChangesStatus() {
        assertEquals(OrderStatus.CREATED, order.getStatus());
        order.setStatus(OrderStatus.PAID);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }
}
