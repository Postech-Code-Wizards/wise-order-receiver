package br.com.wise.orderreceiver.domain;

import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentMethod {
    private PaymentMethodTypeEnum paymentMethodTypeEnum;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardCvv;
}
