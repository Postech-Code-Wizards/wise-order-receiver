package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindProductsBySkuUseCase {

    private final ProductGateway productGateway;

    public List<Product> execute(List<Product> productList){
        return productList.stream()
                .map(productRequest -> {
                    Product product = productGateway.getProductBySKU(productRequest.getSku());
                    return Product.builder()
                            .name(product.getName())
                            .description(product.getDescription())
                            .sku(product.getSku())
                            .price(product.getPrice())
                            .quantity(productRequest.getQuantity())
                            .build();
                })
                .filter(Objects::nonNull)
                .toList();
    }
}
