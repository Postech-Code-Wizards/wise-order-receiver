package br.com.wise.orderreceiver.infrastructure.rest.dtos.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class ProductRequest {

    @NonNull
    private String sku;

    @NonNull
    private Integer quantity;

}
