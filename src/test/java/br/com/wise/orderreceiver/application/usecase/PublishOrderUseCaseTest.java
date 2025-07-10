package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Order;
import br.com.wise.orderreceiver.gateway.SendOrderGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PublishOrderUseCaseTest {

    @Test
    @DisplayName("Should serialize order and send to gateway")
    void shouldSerializeOrderAndSendToGateway() {
        SendOrderGateway sendOrderGateway = mock(SendOrderGateway.class);
        PublishOrderUseCase useCase = new PublishOrderUseCase(sendOrderGateway);
        Order order = Instancio.create(Order.class);

        useCase.execute(order);

        verify(sendOrderGateway, times(1)).send(anyString());
    }

}