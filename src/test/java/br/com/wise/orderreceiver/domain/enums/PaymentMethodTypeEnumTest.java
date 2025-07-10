package br.com.wise.orderreceiver.domain.enums;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTypeEnumTest {

    @Test
    @DisplayName("Should return correct value for each enum constant")
    void shouldReturnCorrectValueForEachEnumConstant() {
        for (PaymentMethodTypeEnum type : PaymentMethodTypeEnum.values()) {
            assertEquals(type.name(), type.getValue());
        }
    }

    @Test
    @DisplayName("Should return value using Instancio generated enum")
    void shouldReturnValueUsingInstancio() {
        PaymentMethodTypeEnum type = Instancio.create(PaymentMethodTypeEnum.class);
        assertEquals(type.name(), type.getValue());
    }
}