package br.com.wise.orderreceiver.infrastructure.rest.controller;

import br.com.wise.orderreceiver.application.facade.OrderReceiverFacade;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.OrderRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderReceiverControllerTest {

    @Test
    @DisplayName("Should return no content when order is created successfully")
    void shouldReturnNoContentWhenOrderIsCreated() {
        OrderReceiverFacade facade = mock(OrderReceiverFacade.class);
        OrderReceiverController controller = new OrderReceiverController(facade);
        OrderRequest request = Instancio.create(OrderRequest.class);

        ResponseEntity<Void> response = controller.createOrder(request);

        assertEquals(204, response.getStatusCodeValue());
        verify(facade).createOrder(request);
    }
}