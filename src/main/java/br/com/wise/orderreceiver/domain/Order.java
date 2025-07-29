package br.com.wise.orderreceiver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Order {

    private Client client;
    private List<Product> productList;
    private PaymentMethod paymentMethod;

}