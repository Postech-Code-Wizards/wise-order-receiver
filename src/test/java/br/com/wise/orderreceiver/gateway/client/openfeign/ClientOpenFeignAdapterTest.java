package br.com.wise.orderreceiver.gateway.client.openfeign;

import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.gateway.client.openfeign.converter.ClientResponseToClient;
import br.com.wise.orderreceiver.gateway.client.openfeign.response.ClientResponse;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import feign.FeignException;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientOpenFeignAdapterTest {

    private final ClientOpenFeignClient clientOpenFeignClient = mock(ClientOpenFeignClient.class);
    private final ClientResponseToClient clientResponseToClient = mock(ClientResponseToClient.class);
    private final ClientOpenFeignAdapter adapter = new ClientOpenFeignAdapter(clientOpenFeignClient, clientResponseToClient);

    @Test
    @DisplayName("Should return client when client is found by identifier")
    void shouldReturnClientWhenClientIsFoundByIdentifier() {
        String identifier = "123456";
        ClientResponse clientResponse = Instancio.create(ClientResponse.class);
        Client expectedClient = Instancio.create(Client.class);

        when(clientOpenFeignClient.getClientByIdentifier(identifier)).thenReturn(clientResponse);
        when(clientResponseToClient.convert(clientResponse)).thenReturn(expectedClient);

        Client result = adapter.getClientByIdentifier(identifier);

        assertEquals(expectedClient, result);
        verify(clientOpenFeignClient).getClientByIdentifier(identifier);
        verify(clientResponseToClient).convert(clientResponse);
    }

    @Test
    @DisplayName("Should throw BusinessException when client is not found")
    void shouldThrowBusinessExceptionWhenClientIsNotFound() {
        String identifier = "notfound";
        FeignException.NotFound notFoundException = mock(FeignException.NotFound.class);

        when(clientOpenFeignClient.getClientByIdentifier(identifier)).thenThrow(notFoundException);
        when(notFoundException.getMessage()).thenReturn("Client not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> adapter.getClientByIdentifier(identifier));
        assertEquals(HttpStatus.NOT_FOUND.name(), exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Client not found", exception.getMessage());
    }
}