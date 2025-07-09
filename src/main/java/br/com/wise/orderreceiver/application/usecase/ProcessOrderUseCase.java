package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.domain.Order;
import br.com.wise.orderreceiver.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessOrderUseCase {

    private final FindClientByIdentifierUseCase findClientByIdentifierUseCase;
    private final FindProductsBySkuUseCase findProductBySkuUseCase;
    private final CalculateTotalOrderUseCase calculateTotalOrder;
    private final ValidatePaymentMethodUseCase validatePaymentMethod;
    private final PublishOrderUseCase publishOrderUseCase;

    public void execute (Order order) {

        validatePaymentMethod.validatePayment(order.getPaymentMethod());
        Client client = findClientByIdentifierUseCase.execute(order.getClient().getIdentifier());
        List<Product> productList = findProductBySkuUseCase.execute(order.getProductList());

        BigDecimal totalValueOrder = calculateTotalOrder.execute(productList);

        Order newOrder = Order.builder()
                .client(client)
                .productList(productList)
                .paymentMethod(order.getPaymentMethod())
                .totalPrice(totalValueOrder)
                .build();

        publishOrderUseCase.execute(newOrder);

    }
}