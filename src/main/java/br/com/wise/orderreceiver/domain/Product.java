package br.com.wise.orderreceiver.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Product {

    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Integer quantity;

    public BigDecimal getTotalPriceProduct() {
        return price.multiply(new BigDecimal(quantity));
    }

}
