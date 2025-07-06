package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.application.strategy.PaymentValidationStrategy;
import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidatePaymentMethodUseCase {

    private final Map<PaymentMethodTypeEnum, PaymentValidationStrategy> strategies;

    public ValidatePaymentMethodUseCase(List<PaymentValidationStrategy> strategyList) {
        this.strategies = new EnumMap<>(PaymentMethodTypeEnum.class);
        for (PaymentValidationStrategy strategy : strategyList) {
            strategies.put(strategy.getType(), strategy);
        }
    }

    public void validatePayment(PaymentMethod paymentMethod) {
        if (paymentMethod == null || paymentMethod.getPaymentMethodTypeEnum() == null) {
            throw new IllegalArgumentException("The payment request and payment type cannot be null.");
        }

        PaymentValidationStrategy strategy = strategies.get(paymentMethod.getPaymentMethodTypeEnum());

        if (strategy == null) {
            throw new UnsupportedOperationException("Payment type not supported for validation: " + paymentMethod.getPaymentMethodTypeEnum());
        }
        strategy.validate(paymentMethod);
    }
}
