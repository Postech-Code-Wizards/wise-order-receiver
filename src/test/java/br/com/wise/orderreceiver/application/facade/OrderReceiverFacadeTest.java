package br.com.wise.orderreceiver.application.facade;

import br.com.wise.orderreceiver.application.facade.converter.ClientRequestToDomain;
import br.com.wise.orderreceiver.application.facade.converter.PaymentMethodRequestToDomain;
import br.com.wise.orderreceiver.application.facade.converter.ProductRequestToDomain;
import br.com.wise.orderreceiver.application.usecase.ProcessOrderUseCase;
import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.domain.Order;
import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.OrderRequest;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.PaymentMethodRequest;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.ProductRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class OrderReceiverFacadeTest {

    private final PaymentMethodRequestToDomain paymentMethodRequestToDomain = mock(PaymentMethodRequestToDomain.class);
    private final ProcessOrderUseCase processOrderUseCase = mock(ProcessOrderUseCase.class);
    private final ProductRequestToDomain productRequestToDomain = mock(ProductRequestToDomain.class);
    private final ClientRequestToDomain clientRequestToDomain = mock(ClientRequestToDomain.class);

    private final OrderReceiverFacade facade = new OrderReceiverFacade(
            paymentMethodRequestToDomain,
            processOrderUseCase,
            productRequestToDomain,
            clientRequestToDomain
    );

    @Test
    @DisplayName("Should create order and call use case with correct order")
    void shouldCreateOrderAndCallUseCaseWithCorrectOrder() {
        OrderRequest orderRequest = mock(OrderRequest.class);
        Client client = Instancio.create(Client.class);
        List<ProductRequest> productRequestList = Instancio.ofList(ProductRequest.class).size(2).create();
        List<Product> productList = Instancio.ofList(Product.class).size(2).create();
        PaymentMethodRequest paymentMethodRequest = Instancio.create(PaymentMethodRequest.class);
        PaymentMethod paymentMethod = Instancio.create(PaymentMethod.class);

        when(orderRequest.getCustomerIdentifier()).thenReturn("123");
        when(orderRequest.getProductRequestList()).thenReturn(productRequestList);
        when(orderRequest.getPaymentMethodRequest()).thenReturn(paymentMethodRequest);
        when(clientRequestToDomain.execute("123")).thenReturn(client);
        for (int i = 0; i < productRequestList.size(); i++) {
            when(productRequestToDomain.convert(productRequestList.get(i))).thenReturn(productList.get(i));
        }
        when(paymentMethodRequestToDomain.convert(paymentMethodRequest)).thenReturn(paymentMethod);

        facade.createOrder(orderRequest);

        verify(clientRequestToDomain).execute("123");
        for (ProductRequest pr : productRequestList) {
            verify(productRequestToDomain).convert(pr);
        }
        verify(paymentMethodRequestToDomain).convert(paymentMethodRequest);
        verify(processOrderUseCase).execute(any(Order.class));
    }
}