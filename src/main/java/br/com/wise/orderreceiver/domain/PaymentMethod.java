package br.com.wise.orderreceiver.domain;

import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentMethod {
    private PaymentMethodTypeEnum paymentMethodTypeEnum;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardCvv;
}
