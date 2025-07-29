package br.com.wise.orderreceiver.application.facade.converter;

import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.PaymentMethodRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMethodRequestToDomain {

    public PaymentMethod convert(PaymentMethodRequest paymentMethodRequest) {
        return new PaymentMethod(paymentMethodRequest.getPaymentMethodTypeEnum(),
                paymentMethodRequest.getCardNumber(),
                paymentMethodRequest.getCardHolderName(),
                paymentMethodRequest.getCardExpiryDate(),
                paymentMethodRequest.getCardCvv());
    }
}
