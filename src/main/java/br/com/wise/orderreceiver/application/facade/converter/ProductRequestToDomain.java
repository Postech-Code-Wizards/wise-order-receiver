package br.com.wise.orderreceiver.application.facade.converter;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRequestToDomain {

    public Product convert(ProductRequest productRequest) {
        return Product.builder()
                .sku(productRequest.getSku())
                .quantity(productRequest.getQuantity())
                .build();
    }

}
