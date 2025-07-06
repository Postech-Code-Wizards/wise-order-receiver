package br.com.wise.orderreceiver.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class Product {

    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Boolean inStock;
    private Integer stock;

    public BigDecimal getTotalPriceProduct(BigInteger quantity) {
        return price.multiply(new BigDecimal(quantity));
    }

}
