package br.com.wise.orderreceiver.application.facade.converter;

import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.PaymentMethodRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMethodRequestToDomain {

    public PaymentMethod convert(PaymentMethodRequest paymentMethodRequest) {
        return PaymentMethod.builder()
                .paymentMethodTypeEnum(paymentMethodRequest.getPaymentMethodTypeEnum())
                .cardNumber(paymentMethodRequest.getCardNumber())
                .cardExpiryDate(paymentMethodRequest.getCardExpiryDate())
                .cardHolderName(paymentMethodRequest.getCardHolderName())
                .cardCvv(paymentMethodRequest.getCardCvv())
                .build();
    }
}
