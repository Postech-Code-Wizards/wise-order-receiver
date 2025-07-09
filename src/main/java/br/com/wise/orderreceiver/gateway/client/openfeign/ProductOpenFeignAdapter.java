package br.com.wise.orderreceiver.gateway.client.openfeign;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.gateway.ProductGateway;
import br.com.wise.orderreceiver.gateway.client.openfeign.converter.GraphQLResponseToProduct;
import br.com.wise.orderreceiver.gateway.client.openfeign.request.GraphQLRequest;
import br.com.wise.orderreceiver.gateway.client.openfeign.response.GraphQLResponse;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductOpenFeignAdapter implements ProductGateway {

    private final ProductOpenFeignClient productOpenFeignClient;
    private final GraphQLResponseToProduct graphQLResponseToProduct;

    @Override
    public Product getProductBySKU(String sku) {

        String query = "query FindProductBySku($sku: String!) { findProductBySku(sku: $sku) { id name description category price sku } }";
        Map<String, Object> variables = new HashMap<>();
        variables.put("sku", sku);

        GraphQLRequest request = GraphQLRequest.builder()
                .query(query)
                .variables(variables)
                .operationName("FindProductBySku")
                .build();
        GraphQLResponse graphQLResponse = productOpenFeignClient.execute(request);
        if(graphQLResponse.hasErrors()) {
            throw new BusinessException(HttpStatus.NOT_FOUND.name()
                    , HttpStatus.NOT_FOUND
                    , "Error fetching product by SKU: " + sku);
        }
        return graphQLResponseToProduct.convert(graphQLResponse);
    }
}
