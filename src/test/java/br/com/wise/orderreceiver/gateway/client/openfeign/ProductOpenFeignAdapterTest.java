package br.com.wise.orderreceiver.gateway.client.openfeign;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.gateway.client.openfeign.converter.GraphQLResponseToProduct;
import br.com.wise.orderreceiver.gateway.client.openfeign.request.GraphQLRequest;
import br.com.wise.orderreceiver.gateway.client.openfeign.response.GraphQLResponse;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductOpenFeignAdapterTest {

    private final ProductOpenFeignClient productOpenFeignClient = mock(ProductOpenFeignClient.class);
    private final GraphQLResponseToProduct graphQLResponseToProduct = mock(GraphQLResponseToProduct.class);
    private final ProductOpenFeignAdapter adapter = new ProductOpenFeignAdapter(productOpenFeignClient, graphQLResponseToProduct);

    @Test
    @DisplayName("Should return product when GraphQL response has no errors")
    void shouldReturnProductWhenGraphQLResponseHasNoErrors() {
        String sku = "SKU123";
        GraphQLResponse response = mock(GraphQLResponse.class);
        Product expectedProduct = Instancio.create(Product.class);

        when(productOpenFeignClient.execute(any(GraphQLRequest.class))).thenReturn(response);
        when(response.hasErrors()).thenReturn(false);
        when(graphQLResponseToProduct.convert(response)).thenReturn(expectedProduct);

        Product result = adapter.getProductBySKU(sku);

        assertEquals(expectedProduct, result);
        verify(productOpenFeignClient).execute(any(GraphQLRequest.class));
        verify(graphQLResponseToProduct).convert(response);
    }

    @Test
    @DisplayName("Should throw BusinessException when GraphQL response has errors")
    void shouldThrowBusinessExceptionWhenGraphQLResponseHasErrors() {
        String sku = "SKU123";
        GraphQLResponse response = mock(GraphQLResponse.class);

        when(productOpenFeignClient.execute(any(GraphQLRequest.class))).thenReturn(response);
        when(response.hasErrors()).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> adapter.getProductBySKU(sku));
        assertEquals(HttpStatus.NOT_FOUND.name(), exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertTrue(exception.getMessage().contains(sku));
    }

    @Test
    @DisplayName("Should build correct GraphQLRequest with given SKU")
    void shouldBuildCorrectGraphQLRequestWithGivenSKU() {
        String sku = "SKU456";
        GraphQLResponse response = mock(GraphQLResponse.class);

        when(productOpenFeignClient.execute(any(GraphQLRequest.class))).thenReturn(response);
        when(response.hasErrors()).thenReturn(true);

        try {
            adapter.getProductBySKU(sku);
        } catch (BusinessException ignored) {}

        ArgumentCaptor<GraphQLRequest> captor = ArgumentCaptor.forClass(GraphQLRequest.class);
        verify(productOpenFeignClient).execute(captor.capture());
        GraphQLRequest request = captor.getValue();

        assertEquals("FindProductBySku", request.getOperationName());
        assertTrue(request.getQuery().contains("findProductBySku"));
        assertEquals(sku, request.getVariables().get("sku"));
    }
}