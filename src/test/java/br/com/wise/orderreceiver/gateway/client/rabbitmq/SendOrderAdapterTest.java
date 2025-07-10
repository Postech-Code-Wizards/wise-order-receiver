package br.com.wise.orderreceiver.gateway.client.rabbitmq;

import br.com.wise.orderreceiver.infrastructure.configuration.OrderMessageConfig;
import br.com.wise.orderreceiver.infrastructure.configuration.RabbitMQConfig;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SendOrderAdapterTest {

    @Test
    @DisplayName("Should send message to RabbitMQ with correct exchange and routing key")
    void shouldSendMessageToRabbitMQ() {
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        SendOrderAdapter adapter = new SendOrderAdapter(rabbitTemplate);
        String message = Instancio.create(String.class);

        adapter.send(message);

        ArgumentCaptor<String> exchangeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> routingKeyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(rabbitTemplate).convertAndSend(
                exchangeCaptor.capture(),
                routingKeyCaptor.capture(),
                messageCaptor.capture()
        );

        assertEquals(RabbitMQConfig.EXCHANGE_NAME, exchangeCaptor.getValue());
        assertEquals(OrderMessageConfig.ORDER_ROUTING_KEY, routingKeyCaptor.getValue());
        assertEquals(message, messageCaptor.getValue());
    }
}