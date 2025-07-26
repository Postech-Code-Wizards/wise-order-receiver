package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessOrderUseCase {

    private final PublishOrderUseCase publishOrderUseCase;

    public void execute (Order order) {
        publishOrderUseCase.execute(order);
    }

}