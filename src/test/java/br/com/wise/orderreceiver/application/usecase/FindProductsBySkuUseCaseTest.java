package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.gateway.ProductGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindProductsBySkuUseCaseTest {

    @Test
    @DisplayName("Should return empty list when input product list is empty")
    void shouldReturnEmptyListWhenInputProductListIsEmpty() {
        ProductGateway productGateway = Mockito.mock(ProductGateway.class);
        FindProductsBySkuUseCase useCase = new FindProductsBySkuUseCase(productGateway);

        List<Product> result = useCase.execute(List.of());

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return products with details from gateway and quantity from request")
    void shouldReturnProductsWithDetailsFromGatewayAndQuantityFromRequest() {
        Product requestProduct = Instancio.of(Product.class)
                .set(field("sku"), "SKU123")
                .set(field("quantity"), 5)
                .create();

        Product gatewayProduct = Instancio.of(Product.class)
                .set(field("sku"), "SKU123")
                .set(field("name"), "Test Product")
                .set(field("description"), "Test Description")
                .set(field("price"), new BigDecimal("99.99"))
                .create();

        ProductGateway productGateway = Mockito.mock(ProductGateway.class);
        Mockito.when(productGateway.getProductBySKU("SKU123")).thenReturn(gatewayProduct);

        FindProductsBySkuUseCase useCase = new FindProductsBySkuUseCase(productGateway);

        List<Product> result = useCase.execute(List.of(requestProduct));

        assertEquals(1, result.size());
        Product resultProduct = result.get(0);
        assertEquals("Test Product", resultProduct.getName());
        assertEquals("Test Description", resultProduct.getDescription());
        assertEquals("SKU123", resultProduct.getSku());
        assertEquals(new BigDecimal("99.99"), resultProduct.getPrice());
        assertEquals(5, resultProduct.getQuantity());
    }
}