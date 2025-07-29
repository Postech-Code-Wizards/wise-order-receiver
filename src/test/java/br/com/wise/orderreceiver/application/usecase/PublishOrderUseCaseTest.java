package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Order;
import br.com.wise.orderreceiver.gateway.SendOrderGateway;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PublishOrderUseCaseTest {

    @Mock
    private SendOrderGateway sendOrderGateway;

    @InjectMocks
    private PublishOrderUseCase publishOrderUseCase;

    private ObjectMapper spyObjectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ObjectMapper realObjectMapper = new ObjectMapper();
        realObjectMapper.registerModule(new JavaTimeModule());
        realObjectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        spyObjectMapper = Mockito.spy(realObjectMapper);
        ReflectionTestUtils.setField(publishOrderUseCase, "objectMapper", spyObjectMapper);
    }

    @Test
    @DisplayName("Should throw BusinessException when JsonProcessingException occurs during order serialization")
    void shouldThrowBusinessExceptionWhenJsonProcessingExceptionOccurs() throws JsonProcessingException {
        Order order = Instancio.create(Order.class);

        doThrow(new JsonProcessingException("Test JSON processing error") {})
                .when(spyObjectMapper).writeValueAsString(order);

        BusinessException thrownException = assertThrows(BusinessException.class, () -> publishOrderUseCase.execute(order));

        assertTrue(thrownException.getMessage().contains("Error converting order to JSON"));
        assertEquals(HttpStatus.BAD_REQUEST.name(), thrownException.getCode());

        verify(sendOrderGateway, never()).send(anyString());
    }

    @Test
    @DisplayName("Should call SendOrderGateway when order serialization is successful")
    void shouldCallSendOrderGatewayWhenSerializationIsSuccessful() {
        Order order = Instancio.create(Order.class);

        publishOrderUseCase.execute(order);

        verify(sendOrderGateway, times(1)).send(anyString());
    }
}