package br.com.wise.orderreceiver.application.strategy;

import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardValidationStrategyTest {

    private final CreditCardValidationStrategy strategy = new CreditCardValidationStrategy();

    @Test
    @DisplayName("Should return CREDIT_CARD as payment method type")
    void shouldReturnCreditCardType() {
        assertEquals(PaymentMethodTypeEnum.CREDIT_CARD, strategy.getType());
    }

    @Test
    @DisplayName("Should validate successfully with valid credit card data")
    void shouldValidateSuccessfullyWithValidData() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "4111111111111111")
                .set(field("cardExpiryDate"), "12/25")
                .set(field("cardCvv"), "123")
                .create();

        assertDoesNotThrow(() -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException for invalid card number")
    void shouldThrowBusinessExceptionForInvalidCardNumber() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "123")
                .set(field("cardExpiryDate"), "12/25")
                .set(field("cardCvv"), "123")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException for empty card number")
    void shouldThrowBusinessExceptionForEmptyCardNumber() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "")
                .set(field("cardExpiryDate"), "12/25")
                .set(field("cardCvv"), "123")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException for larger size thirteen card number")
    void shouldThrowBusinessExceptionForLargerSizeThirteenCardNumber() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "123456789101213141516")
                .set(field("cardExpiryDate"), "12/25")
                .set(field("cardCvv"), "123")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException for invalid card expiry date")
    void shouldThrowBusinessExceptionForInvalidCardExpiryDate() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "4111111111111111")
                .set(field("cardExpiryDate"), "2025-12")
                .set(field("cardCvv"), "123")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException for empty card expiry date")
    void shouldThrowBusinessExceptionForEmptyCardExpiryDate() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "4111111111111111")
                .set(field("cardExpiryDate"), "")
                .set(field("cardCvv"), "123")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException for invalid card CVV")
    void shouldThrowBusinessExceptionForInvalidCardCvv() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "4111111111111111")
                .set(field("cardExpiryDate"), "12/25")
                .set(field("cardCvv"), "1")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException larger size of four card CVV")
    void shouldThrowBusinessExceptionForLargerSizeOfFourCardCvv() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "4111111111111111")
                .set(field("cardExpiryDate"), "12/25")
                .set(field("cardCvv"), "12345")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException for empty card CVV")
    void shouldThrowBusinessExceptionForEmptyCardCvv() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "4111111111111111")
                .set(field("cardExpiryDate"), "12/25")
                .set(field("cardCvv"), "")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    private static org.instancio.Selector field(String name) {
        return org.instancio.Select.field(PaymentMethod.class, name);
    }
}