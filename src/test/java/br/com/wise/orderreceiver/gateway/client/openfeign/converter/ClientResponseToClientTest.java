package br.com.wise.orderreceiver.gateway.client.openfeign.converter;

import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.gateway.client.openfeign.response.ClientResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientResponseToClientTest {

    private final ClientResponseToClient converter = new ClientResponseToClient();

    @Test
    @DisplayName("Should convert ClientResponse to Client domain object")
    void shouldConvertClientResponseToClient() {
        ClientResponse clientResponse = Instancio.create(ClientResponse.class);

        Client result = converter.convert(clientResponse);

        assertEquals(clientResponse.getId(), result.getId());
        assertEquals(clientResponse.getNome(), result.getName());
        assertEquals(clientResponse.getCpf(), result.getIdentifier());
        assertEquals(clientResponse.getDataNascimento(), result.getDatOfBirth());
    }
}