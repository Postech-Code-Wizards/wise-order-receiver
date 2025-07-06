package br.com.wise.orderreceiver.gateway.openfeign.converter;

import br.com.wise.orderreceiver.domain.Product;
import br.com.wise.orderreceiver.gateway.openfeign.response.GraphQLResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
public class GraphQLResponseToProduct {

    private final ObjectMapper objectMapper;

    public Product convert(GraphQLResponse graphQLResponse) {

        @SuppressWarnings("unchecked")
        LinkedHashMap<String, Object> productMap = (LinkedHashMap<String, Object>) graphQLResponse.getData().get("findProductBySku");
        return objectMapper.convertValue(productMap, Product.class);
    }
}
