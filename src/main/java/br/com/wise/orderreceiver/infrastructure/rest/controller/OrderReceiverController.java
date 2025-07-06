package br.com.wise.orderreceiver.infrastructure.rest.controller;

import br.com.wise.orderreceiver.application.facade.OrderReceiverFacade;
import br.com.wise.orderreceiver.infrastructure.rest.dtos.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrderReceiverController implements OrderReceiverApi {

    private final OrderReceiverFacade orderReceiverFacade;

    @Override
    public ResponseEntity<Void> createOrder(OrderRequest orderRequest) {
        orderReceiverFacade.createOrder(orderRequest);
        return ResponseEntity.noContent().build();
    }

}