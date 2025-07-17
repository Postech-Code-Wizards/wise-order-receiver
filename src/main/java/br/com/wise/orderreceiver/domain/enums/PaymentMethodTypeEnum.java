package br.com.wise.orderreceiver.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentMethodTypeEnum {
    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD");

    private final String value;

    PaymentMethodTypeEnum(String value) {
        this.value = value;
    }

}
