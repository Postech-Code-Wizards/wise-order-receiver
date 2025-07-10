package br.com.wise.orderreceiver.gateway.client.openfeign.response;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphQLResponseTest {

    @Test
    @DisplayName("Should return true when errors list is not null and not empty")
    void shouldReturnTrueWhenErrorsListIsNotNullAndNotEmpty() {
        GraphQLResponse response = Instancio.create(GraphQLResponse.class);
        response.setErrors(List.of(Map.of("error", "some error")));
        assertTrue(response.hasErrors());
    }

    @Test
    @DisplayName("Should return false when errors list is null")
    void shouldReturnFalseWhenErrorsListIsNull() {
        GraphQLResponse response = Instancio.create(GraphQLResponse.class);
        response.setErrors(null);
        assertFalse(response.hasErrors());
    }

    @Test
    @DisplayName("Should return false when errors list is empty")
    void shouldReturnFalseWhenErrorsListIsEmpty() {
        GraphQLResponse response = Instancio.create(GraphQLResponse.class);
        response.setErrors(List.of());
        assertFalse(response.hasErrors());
    }
}