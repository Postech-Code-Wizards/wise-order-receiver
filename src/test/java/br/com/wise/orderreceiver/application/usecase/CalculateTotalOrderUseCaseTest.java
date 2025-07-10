package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Product;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateTotalOrderUseCaseTest {

    private final CalculateTotalOrderUseCase useCase = new CalculateTotalOrderUseCase();

    @Test
    @DisplayName("Should return zero when product list is empty")
    void shouldReturnZeroWhenProductListIsEmpty() {
        List<Product> products = List.of();
        BigDecimal total = useCase.execute(products);
        assertEquals(BigDecimal.ZERO, total);
    }

    @Test
    @DisplayName("Should calculate total order value for a list with one product")
    void shouldCalculateTotalOrderValueForListWithOneProduct() {
        Product product = Instancio.of(Product.class)
                .set(field("price"), new BigDecimal("15.00"))
                .set(field("quantity"), 1)
                .create();
        List<Product> products = List.of(product);
        BigDecimal total = useCase.execute(products);
        assertEquals(new BigDecimal("15.00"), total);
    }

    @Test
    @DisplayName("Should calculate total order value for a list of products")
    void shouldCalculateTotalOrderValueForListOfProducts() {
        Product product1 = Instancio.of(Product.class)
                .set(field("price"), new BigDecimal("10.50"))
                .set(field("quantity"), 1)
                .create();
        Product product2 = Instancio.of(Product.class)
                .set(field("price"), new BigDecimal("20.00"))
                .set(field("quantity"), 1)
                .create();
        List<Product> products = List.of(product1, product2);
        BigDecimal total = useCase.execute(products);
        assertEquals(new BigDecimal("30.50"), total);
    }

}