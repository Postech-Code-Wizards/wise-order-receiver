package br.com.wise.orderreceiver.infrastructure.rest.dtos.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class ProductRequest {

    @NonNull
    private String sku;

    @NonNull
    private Integer quantity;

}
