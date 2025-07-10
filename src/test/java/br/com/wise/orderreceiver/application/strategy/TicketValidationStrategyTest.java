package br.com.wise.orderreceiver.application.strategy;


import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketValidationStrategyTest {

    private final TicketValidationStrategy strategy = new TicketValidationStrategy();

    @Test
    @DisplayName("Should return TICKET as payment method type")
    void shouldReturnTicketType() {
        assertEquals(PaymentMethodTypeEnum.TICKET, strategy.getType());
    }

    @Test
    @DisplayName("Should validate successfully when no card details are present")
    void shouldValidateSuccessfullyWhenNoCardDetails() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), null)
                .set(field("cardExpiryDate"), null)
                .set(field("cardCvv"), null)
                .create();

        assertDoesNotThrow(() -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException when card number is present")
    void shouldThrowBusinessExceptionWhenCardNumberPresent() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), "1234567890123456")
                .set(field("cardExpiryDate"), null)
                .set(field("cardCvv"), null)
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException when card expiry date is present")
    void shouldThrowBusinessExceptionWhenCardExpiryDatePresent() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), null)
                .set(field("cardExpiryDate"), "12/25")
                .set(field("cardCvv"), null)
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    @Test
    @DisplayName("Should throw BusinessException when card CVV is present")
    void shouldThrowBusinessExceptionWhenCardCvvPresent() {
        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("cardNumber"), null)
                .set(field("cardExpiryDate"), null)
                .set(field("cardCvv"), "123")
                .create();

        assertThrows(BusinessException.class, () -> strategy.validate(paymentMethod));
    }

    private static org.instancio.Selector field(String name) {
        return org.instancio.Select.field(PaymentMethod.class, name);
    }
}