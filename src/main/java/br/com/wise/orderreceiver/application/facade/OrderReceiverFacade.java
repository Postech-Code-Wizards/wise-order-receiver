package br.com.wise.orderreceiver.application.facade;

import br.com.wise.orderreceiver.application.facade.converter.PaymentMethodRequestToDomain;
import br.com.wise.orderreceiver.application.usecase.CalculateTotalOrderUseCase;
import br.com.wise.orderreceiver.application.usecase.FindClientByIdentifierUseCase;
import br.com.wise.orderreceiver.application.usecase.FindProductsBySkuUseCase;
import br.com.wise.orderreceiver.application.usecase.ValidatePaymentMethodUseCase;
import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.domain.PaymentMethod;
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

    private final FindClientByIdentifierUseCase findClientByIdentifierUseCase;
    private final FindProductsBySkuUseCase findProductBySkuUseCase;
    private final CalculateTotalOrderUseCase calculateTotalOrder;
    private final ValidatePaymentMethodUseCase validatePaymentMethod;
    private final PaymentMethodRequestToDomain paymentMethodRequestToDomain;

    public void createOrder(OrderRequest orderRequest) {
        Client client = findClientByIdentifierUseCase.execute(orderRequest.getCustomerIdentifier());
        List<Product> productList = findProductBySkuUseCase.execute(orderRequest.getProductRequestList());

        PaymentMethod paymentMethod = paymentMethodRequestToDomain.convert(orderRequest.getPaymentMethodRequest());
        validatePaymentMethod.validatePayment(paymentMethod);

        // Calcula o valor total do pedido

        // Envia mensagem do pedido para o RabbitMQ

    }

}
