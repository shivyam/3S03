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
    void testProcessOrder_ValidPayment_NoDiscount_ReturnsCorrectTotal() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, null, "card");
        
        // Subtotal: 100.0, Tax: 20.0 (20%), Total: 120.0
        assertEquals(120.0, total, 0.01);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void testProcessOrder_ValidPayment_WithStudent10Discount() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, "STUDENT10", "card");
        
        // Subtotal: 100.0, After discount: 90.0, Tax: 18.0 (20%), Total: 108.0
        assertEquals(108.0, total, 0.01);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void testProcessOrder_ValidPayment_WithBlackFridayDiscount() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, "BLACKFRIDAY", "paypal");
        
        // Subtotal: 100.0, After discount: 70.0, Tax: 14.0 (20%), Total: 84.0
        assertEquals(84.0, total, 0.01);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void testProcessOrder_InvalidPaymentMethod_ReturnsZero() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, null, "crypto");
        
        assertEquals(0.0, total, 0.01);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void testProcessOrder_NullPaymentMethod_ReturnsZero() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, null, null);
        
        assertEquals(0.0, total, 0.01);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void testProcessOrder_CardPayment_EmptyOrder() {
        Order order = new Order();
        
        double total = orderService.processOrder(order, null, "card");
        
        // No items, no tax: 0.0
        assertEquals(0.0, total, 0.01);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void testProcessOrder_PayPalPayment_MultipleItems() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        order.addItem(new OrderItem("Mouse", 2, 25.0));
        
        double total = orderService.processOrder(order, null, "paypal");
        
        // Subtotal: 150.0, Tax: 30.0 (20%), Total: 180.0
        assertEquals(180.0, total, 0.01);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void testProcessOrder_MultipleItems_WithDiscount() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 1000.0));
        order.addItem(new OrderItem("Mouse", 1, 50.0));
        
        double total = orderService.processOrder(order, "STUDENT10", "card");
        
        // Subtotal: 1050.0, After discount: 945.0, Tax: 189.0 (20%), Total: 1134.0
        assertEquals(1134.0, total, 0.01);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void testProcessOrder_UnknownDiscountCode_NoDiscountApplied() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, "UNKNOWN", "card");
        
        // Subtotal: 100.0, Tax: 20.0 (20%), Total: 120.0 (no discount)
        assertEquals(120.0, total, 0.01);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void testProcessOrder_BlankDiscountCode_NoDiscountApplied() {
        Order order = new Order();
        order.addItem(new OrderItem("Laptop", 1, 100.0));
        
        double total = orderService.processOrder(order, "   ", "card");
        
        // Subtotal: 100.0, Tax: 20.0 (20%), Total: 120.0
        assertEquals(120.0, total, 0.01);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }
}
