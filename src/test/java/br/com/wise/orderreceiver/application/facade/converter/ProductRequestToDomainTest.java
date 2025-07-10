package br.com.wise.orderreceiver.application.facade.converter;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.ProductRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestToDomainTest {

    private final ProductRequestToDomain converter = new ProductRequestToDomain();

    @Test
    @DisplayName("Should convert ProductRequest to Product domain object")
    void shouldConvertProductRequestToDomain() {
        ProductRequest request = Instancio.create(ProductRequest.class);

        Product result = converter.convert(request);

        assertEquals(request.getSku(), result.getSku());
        assertEquals(request.getQuantity(), result.getQuantity());
    }
}