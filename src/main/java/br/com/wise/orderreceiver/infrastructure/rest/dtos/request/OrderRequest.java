package br.com.wise.orderreceiver.infrastructure.rest.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @NotNull
    @Valid
    private String customerIdentifier;

    @NonNull
    @Valid
    private List<ProductRequest> productRequestList;

    @NonNull
    @Valid
    private PaymentMethodRequest paymentMethodRequest;
}
