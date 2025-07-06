package br.com.wise.orderreceiver.application.strategy;

import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;

public interface PaymentValidationStrategy {

    void validate(PaymentMethod request);

    PaymentMethodTypeEnum getType();
}
