package br.com.wise.orderreceiver.infrastructure.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class OrderMessageConfigTest {

    @InjectMocks
    private OrderMessageConfig orderMessageConfig;

    @Mock
    private DirectExchange mockDirectExchange; // Mock the DirectExchange dependency for the binding test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create an order queue bean with the correct name and durability")
    void shouldCreateOrderQueueBeanWithCorrectNameAndDurability() {

        Queue orderQueue = orderMessageConfig.orderQueue();

        assertNotNull(orderQueue, "Order Queue should not be null");

        assertEquals(OrderMessageConfig.ORDER_QUEUE_NAME, orderQueue.getName(), "Order Queue name should match the constant");

        assertTrue(orderQueue.isDurable(), "Order Queue should be durable");
    }

    @Test
    @DisplayName("Should create an order binding bean with the correct queue, exchange, and routing key")
    void shouldCreateOrderBindingBeanWithCorrectProperties() {

        Queue orderQueue = orderMessageConfig.orderQueue();

        when(mockDirectExchange.getName()).thenReturn("test_exchange_name");

        Binding orderBinding = orderMessageConfig.orderBinding(orderQueue, mockDirectExchange);

        assertNotNull(orderBinding, "Order Binding should not be null");

        assertEquals(OrderMessageConfig.ORDER_QUEUE_NAME, orderBinding.getDestination(), "Binding destination should be the order queue name");

        assertEquals("test_exchange_name", orderBinding.getExchange(), "Binding exchange should match the mocked exchange name");

        assertEquals(OrderMessageConfig.ORDER_ROUTING_KEY, orderBinding.getRoutingKey(), "Binding routing key should match the constant");

        assertEquals(Binding.DestinationType.QUEUE, orderBinding.getDestinationType(), "Binding destination type should be QUEUE");
    }
}