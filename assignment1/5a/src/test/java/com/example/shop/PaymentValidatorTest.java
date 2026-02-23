package com.example.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentValidatorTest {

    private PaymentValidator paymentValidator;

    @BeforeEach
    void setUp() {
        paymentValidator = new PaymentValidator();
    }

    @Test
    void testIsPaymentMethodValid_Card_ReturnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("card"));
    }

    @Test
    void testIsPaymentMethodValid_PayPal_UpperCase_ReturnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("PAYPAL"));
    }

    @Test
    void testIsPaymentMethodValid_Crypto_UpperCase_ReturnsFalse() {
        assertFalse(paymentValidator.isPaymentMethodValid("CRYPTO"));
    }

    @Test
    void testIsPaymentMethodValid_Null_ReturnsFalse() {
        paymentValidator.isPaymentMethodValid(null);
    }

    @Test
    void testIsPaymentMethodValid_EmptyString_ThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            paymentValidator.isPaymentMethodValid("");
        });
    }
}
