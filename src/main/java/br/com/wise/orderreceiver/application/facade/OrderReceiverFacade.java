package br.com.wise.orderreceiver.application.facade;

import br.com.wise.orderreceiver.application.facade.converter.ClientRequestToDomain;
import br.com.wise.orderreceiver.application.facade.converter.PaymentMethodRequestToDomain;
import br.com.wise.orderreceiver.application.facade.converter.ProductRequestToDomain;
import br.com.wise.orderreceiver.application.usecase.ProcessOrderUseCase;
import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.domain.Order;
import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderReceiverFacade {

    private final PaymentMethodRequestToDomain paymentMethodRequestToDomain;
    private final ProcessOrderUseCase orderReceiverUseCase;
    private final ProductRequestToDomain productRequestToDomain;
    private final ClientRequestToDomain clientRequestToDomain;

    public void createOrder(OrderRequest orderRequest) {

        Client client = clientRequestToDomain.execute(orderRequest.getCustomerIdentifier());

        List<Product> productList = orderRequest.getProductRequestList().stream()
                .map(productRequestToDomain::convert)
                .toList();

        Order order = Order.builder()
                .client(client)
                .productList(productList)
                .paymentMethod(paymentMethodRequestToDomain.convert(orderRequest.getPaymentMethodRequest()))
                .build();

        orderReceiverUseCase.execute(order);

    }

}
