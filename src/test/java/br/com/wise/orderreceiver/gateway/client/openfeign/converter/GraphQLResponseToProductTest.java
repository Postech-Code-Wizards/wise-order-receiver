package br.com.wise.orderreceiver.gateway.client.openfeign.converter;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.gateway.client.openfeign.response.GraphQLResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GraphQLResponseToProductTest {

    @Test
    @DisplayName("Should convert GraphQLResponse to Product domain object")
    void shouldConvertGraphQLResponseToProduct() {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        GraphQLResponseToProduct converter = new GraphQLResponseToProduct(objectMapper);

        Product expectedProduct = Instancio.create(Product.class);
        LinkedHashMap<String, Object> productMap = new LinkedHashMap<>();
        GraphQLResponse graphQLResponse = mock(GraphQLResponse.class);
        Map<String, Object> dataMap = mock(Map.class);

        when(graphQLResponse.getData()).thenReturn(dataMap);
        when(dataMap.get("findProductBySku")).thenReturn(productMap);
        when(objectMapper.convertValue(productMap, Product.class)).thenReturn(expectedProduct);

        Product result = converter.convert(graphQLResponse);

        assertEquals(expectedProduct, result);
    }
}