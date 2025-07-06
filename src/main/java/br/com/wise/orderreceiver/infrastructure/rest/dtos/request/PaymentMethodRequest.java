package br.com.wise.orderreceiver.infrastructure.rest.dtos.request;

import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;
import lombok.Data;

@Data
public class PaymentMethodRequest {
    private PaymentMethodTypeEnum paymentMethodTypeEnum;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardCvv;
}
