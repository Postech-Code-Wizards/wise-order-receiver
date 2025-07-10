package br.com.wise.orderreceiver.application.facade.converter;

import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.PaymentMethodRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodRequestToDomainTest {

    private final PaymentMethodRequestToDomain converter = new PaymentMethodRequestToDomain();

    @Test
    @DisplayName("Should convert PaymentMethodRequest to PaymentMethod domain object")
    void shouldConvertPaymentMethodRequestToDomain() {
        PaymentMethodRequest request = Instancio.create(PaymentMethodRequest.class);

        PaymentMethod result = converter.convert(request);

        assertEquals(request.getPaymentMethodTypeEnum(), result.getPaymentMethodTypeEnum());
        assertEquals(request.getCardNumber(), result.getCardNumber());
        assertEquals(request.getCardExpiryDate(), result.getCardExpiryDate());
        assertEquals(request.getCardHolderName(), result.getCardHolderName());
        assertEquals(request.getCardCvv(), result.getCardCvv());
    }
}