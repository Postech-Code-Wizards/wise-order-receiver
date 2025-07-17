package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.application.strategy.PaymentValidationStrategy;
import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ValidatePaymentMethodUseCaseTest {

    @Test
    @DisplayName("Should validate payment using the correct strategy")
    void shouldValidatePaymentUsingCorrectStrategy() {
        PaymentValidationStrategy strategy = mock(PaymentValidationStrategy.class);
        when(strategy.getType()).thenReturn(PaymentMethodTypeEnum.CREDIT_CARD);

        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("paymentMethodTypeEnum"), PaymentMethodTypeEnum.CREDIT_CARD)
                .create();

        ValidatePaymentMethodUseCase useCase = new ValidatePaymentMethodUseCase(List.of(strategy));
        useCase.validatePayment(paymentMethod);

        verify(strategy, times(1)).validate(paymentMethod);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when payment method is null")
    void shouldThrowIllegalArgumentExceptionWhenPaymentMethodIsNull() {
        PaymentValidationStrategy strategy = mock(PaymentValidationStrategy.class);
        when(strategy.getType()).thenReturn(PaymentMethodTypeEnum.DEBIT_CARD);

        ValidatePaymentMethodUseCase useCase = new ValidatePaymentMethodUseCase(List.of(strategy));

        assertThrows(IllegalArgumentException.class, () -> useCase.validatePayment(null));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when payment type is null")
    void shouldThrowIllegalArgumentExceptionWhenPaymentTypeIsNull() {
        PaymentValidationStrategy strategy = mock(PaymentValidationStrategy.class);
        when(strategy.getType()).thenReturn(PaymentMethodTypeEnum.DEBIT_CARD);

        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("paymentMethodTypeEnum"), null)
                .create();

        ValidatePaymentMethodUseCase useCase = new ValidatePaymentMethodUseCase(List.of(strategy));

        assertThrows(IllegalArgumentException.class, () -> useCase.validatePayment(paymentMethod));
    }

    @Test
    @DisplayName("Should throw UnsupportedOperationException when payment type is not supported")
    void shouldThrowUnsupportedOperationExceptionWhenPaymentTypeIsNotSupported() {
        PaymentValidationStrategy strategy = mock(PaymentValidationStrategy.class);
        when(strategy.getType()).thenReturn(PaymentMethodTypeEnum.DEBIT_CARD);

        PaymentMethod paymentMethod = Instancio.of(PaymentMethod.class)
                .set(field("paymentMethodTypeEnum"), PaymentMethodTypeEnum.CREDIT_CARD)
                .create();

        ValidatePaymentMethodUseCase useCase = new ValidatePaymentMethodUseCase(List.of(strategy));

        assertThrows(UnsupportedOperationException.class, () -> useCase.validatePayment(paymentMethod));
    }
}