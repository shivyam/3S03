package com.example.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void testProcessOrder_ValidPayment_WithStudent10Discount() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, "STUDENT10", "card");
        
        assertTrue(total > 0);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void testProcessOrder_ValidPayment_WithBlackFridayDiscount() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, "BLACKFRIDAY", "paypal");
        assertTrue(total >= 0);
    }

    @Test
    void testProcessOrder_NullPaymentMethod_ReturnsZero() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, null, null);
        
        assertEquals(0.0, total, 0.01);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }
}
