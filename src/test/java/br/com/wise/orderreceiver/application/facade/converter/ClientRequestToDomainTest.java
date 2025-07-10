package br.com.wise.orderreceiver.application.facade.converter;

import br.com.wise.orderreceiver.domain.Client;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientRequestToDomainTest {

    private final ClientRequestToDomain converter = new ClientRequestToDomain();

    @Test
    @DisplayName("Should create Client domain object with given identifier")
    void shouldCreateClientWithGivenIdentifier() {
        String identifier = Instancio.create(String.class);

        Client result = converter.execute(identifier);

        assertNotNull(result);
        assertEquals(identifier, result.getIdentifier());
    }
}