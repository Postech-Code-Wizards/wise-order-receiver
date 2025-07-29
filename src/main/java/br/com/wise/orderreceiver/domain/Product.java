package br.com.wise.orderreceiver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private String sku;
    private Integer quantity;

}