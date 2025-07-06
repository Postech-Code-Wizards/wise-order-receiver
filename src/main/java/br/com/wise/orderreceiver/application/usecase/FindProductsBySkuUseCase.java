package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.gateway.ProductGateway;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindProductsBySkuUseCase {

    private final ProductGateway productGateway;

    public List<Product> execute(List<ProductRequest> productRequestList){
        return productRequestList.stream()
                .map(ProductRequest::getSku)
                .map(productGateway::getProductBySKU)
                .filter(Objects::nonNull)
                .toList();
    }
}
