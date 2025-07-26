package br.com.wise.orderreceiver.infrastructure.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import static org.junit.jupiter.api.Assertions.*;

class RabbitMQConfigTest {

    private RabbitMQConfig rabbitMQConfig;

    @Mock
    private ConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rabbitMQConfig = new RabbitMQConfig();
    }

    @Test
    @DisplayName("Should create a DirectExchange bean with the correct name")
    void shouldCreateDirectExchangeBeanWithCorrectName() {
        DirectExchange directExchange = rabbitMQConfig.directExchange();
        assertNotNull(directExchange, "DirectExchange should not be null");
        assertEquals(RabbitMQConfig.EXCHANGE_NAME, directExchange.getName(), "Exchange name should match EXCHANGE_NAME");
    }

    @Test
    @DisplayName("Should create a Jackson2JsonMessageConverter bean")
    void shouldCreateJackson2JsonMessageConverterBean() {
        Jackson2JsonMessageConverter converter = rabbitMQConfig.jsonMessageConverter();
        assertNotNull(converter, "Jackson2JsonMessageConverter should not be null");
    }

    @Test
    @DisplayName("Should create a RabbitTemplate bean with the correct message converter and connection factory")
    void shouldCreateRabbitTemplateBeanWithCorrectConverterAndConnectionFactory() {

        RabbitTemplate rabbitTemplate = rabbitMQConfig.rabbitTemplate(connectionFactory);
        assertNotNull(rabbitTemplate, "RabbitTemplate should not be null");

        assertNotNull(rabbitTemplate.getMessageConverter(), "Message converter should be set");
        assertInstanceOf(Jackson2JsonMessageConverter.class, rabbitTemplate.getMessageConverter(), "Message converter should be an instance of Jackson2JsonMessageConverter");
    }

}