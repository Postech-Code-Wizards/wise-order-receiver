package br.com.wise.orderreceiver.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class Order {

    private BigDecimal totalPrice;
    private Client client;
    private List<Product> productList;
    private PaymentMethod paymentMethod;

}