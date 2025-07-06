package br.com.wise.orderreceiver.domain.enums;

public enum PaymentMethodTypeEnum {
    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD"),
    PIX("PIX"),
    TICKET("TICKET");

    private final String value;

    PaymentMethodTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
