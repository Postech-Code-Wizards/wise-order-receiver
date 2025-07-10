package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.domain.Order;
import br.com.wise.orderreceiver.domain.Product;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProcessOrderUseCaseTest {

    @Test
    @DisplayName("Should process order and publish new order with calculated total and enriched data")
    void shouldProcessOrderAndPublishNewOrderWithCalculatedTotalAndEnrichedData() {
        FindClientByIdentifierUseCase findClientByIdentifierUseCase = mock(FindClientByIdentifierUseCase.class);
        FindProductsBySkuUseCase findProductsBySkuUseCase = mock(FindProductsBySkuUseCase.class);
        CalculateTotalOrderUseCase calculateTotalOrderUseCase = mock(CalculateTotalOrderUseCase.class);
        ValidatePaymentMethodUseCase validatePaymentMethodUseCase = mock(ValidatePaymentMethodUseCase.class);
        PublishOrderUseCase publishOrderUseCase = mock(PublishOrderUseCase.class);

        ProcessOrderUseCase useCase = new ProcessOrderUseCase(
                findClientByIdentifierUseCase,
                findProductsBySkuUseCase,
                calculateTotalOrderUseCase,
                validatePaymentMethodUseCase,
                publishOrderUseCase
        );

        Order inputOrder = Instancio.of(Order.class)
                .set(field("productList"), List.of(Instancio.create(Product.class)))
                .create();
        Client client = Instancio.create(Client.class);
        List<Product> enrichedProducts = List.of(Instancio.create(Product.class));
        BigDecimal total = new BigDecimal("123.45");

        when(findClientByIdentifierUseCase.execute(inputOrder.getClient().getIdentifier())).thenReturn(client);
        when(findProductsBySkuUseCase.execute(inputOrder.getProductList())).thenReturn(enrichedProducts);
        when(calculateTotalOrderUseCase.execute(enrichedProducts)).thenReturn(total);

        useCase.execute(inputOrder);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(validatePaymentMethodUseCase).validatePayment(inputOrder.getPaymentMethod());
        verify(findClientByIdentifierUseCase).execute(inputOrder.getClient().getIdentifier());
        verify(findProductsBySkuUseCase).execute(inputOrder.getProductList());
        verify(calculateTotalOrderUseCase).execute(enrichedProducts);
        verify(publishOrderUseCase).execute(orderCaptor.capture());

        Order publishedOrder = orderCaptor.getValue();
        assertEquals(client, publishedOrder.getClient());
        assertEquals(enrichedProducts, publishedOrder.getProductList());
        assertEquals(inputOrder.getPaymentMethod(), publishedOrder.getPaymentMethod());
        assertEquals(total, publishedOrder.getTotalPrice());
    }
}