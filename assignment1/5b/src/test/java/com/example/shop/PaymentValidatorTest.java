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
    void testIsPaymentMethodValid_Card_UpperCase_ReturnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("CARD"));
    }

    @Test
    void testIsPaymentMethodValid_Card_MixedCase_ReturnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("CaRd"));
    }

    @Test
    void testIsPaymentMethodValid_PayPal_ReturnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("paypal"));
    }

    @Test
    void testIsPaymentMethodValid_PayPal_UpperCase_ReturnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("PAYPAL"));
    }

    @Test
    void testIsPaymentMethodValid_PayPal_MixedCase_ReturnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("PayPal"));
    }

    @Test
    void testIsPaymentMethodValid_Crypto_ReturnsFalse() {
        assertFalse(paymentValidator.isPaymentMethodValid("crypto"));
    }

    @Test
    void testIsPaymentMethodValid_Crypto_UpperCase_ReturnsFalse() {
        assertFalse(paymentValidator.isPaymentMethodValid("CRYPTO"));
    }

    @Test
    void testIsPaymentMethodValid_Null_ReturnsFalse() {
        assertFalse(paymentValidator.isPaymentMethodValid(null));
    }

    @Test
    void testIsPaymentMethodValid_UnknownMethod_ThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            paymentValidator.isPaymentMethodValid("bitcoin");
        });
    }

    @Test
    void testIsPaymentMethodValid_EmptyString_ThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            paymentValidator.isPaymentMethodValid("");
        });
    }

    @Test
    void testIsPaymentMethodValid_BlankString_ThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            paymentValidator.isPaymentMethodValid("   ");
        });
    }
}
