package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.gateway.ClientGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindClientByIdentifierUseCaseTest {

    @Test
    @DisplayName("Should return client when identifier exists")
    void shouldReturnClientWhenIdentifierExists() {
        String identifier = "123456";
        Client expectedClient = Instancio.create(Client.class);
        ClientGateway clientGateway = Mockito.mock(ClientGateway.class);
        Mockito.when(clientGateway.getClientByIdentifier(identifier)).thenReturn(expectedClient);

        FindClientByIdentifierUseCase useCase = new FindClientByIdentifierUseCase(clientGateway);
        Client result = useCase.execute(identifier);

        assertEquals(expectedClient, result);
    }

}