package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateTotalOrderUseCase {

    public BigDecimal execute(List<Product> productList) {
        return productList.stream()
                .map(Product::getTotalPriceProduct)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
