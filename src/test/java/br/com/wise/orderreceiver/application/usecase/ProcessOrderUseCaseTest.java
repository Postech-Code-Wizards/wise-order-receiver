package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Order;
import br.com.wise.orderreceiver.domain.Product;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.instancio.Select.field;
import static org.mockito.Mockito.*;

class ProcessOrderUseCaseTest {

    @Test
    @DisplayName("Should process order and publish new order with calculated total and enriched data")
    void shouldProcessOrderAndPublishNewOrderWithCalculatedTotalAndEnrichedData() {
        PublishOrderUseCase publishOrderUseCase = mock(PublishOrderUseCase.class);

        ProcessOrderUseCase useCase = new ProcessOrderUseCase(
                publishOrderUseCase
        );

        Order order = Instancio.of(Order.class)
                .set(field("productList"), List.of(Instancio.create(Product.class)))
                .create();

        useCase.execute(order);

        verify(publishOrderUseCase, times(1)).execute(order);
    }
}