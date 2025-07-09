package br.com.wise.orderreceiver.gateway.client.rabbitmq;

import br.com.wise.orderreceiver.gateway.SendOrderGateway;
import br.com.wise.orderreceiver.infrastructure.configuration.OrderMessageConfig;
import br.com.wise.orderreceiver.infrastructure.configuration.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendOrderAdapter implements SendOrderGateway {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String message) {

        log.info("[{}] Sending ID message to RabbitMQ.", this.getClass().getSimpleName());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                OrderMessageConfig.ORDER_ROUTING_KEY,
                message);
    }
}
