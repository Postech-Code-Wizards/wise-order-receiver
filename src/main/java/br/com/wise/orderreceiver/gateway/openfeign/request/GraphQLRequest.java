package br.com.wise.orderreceiver.gateway.openfeign.request;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class GraphQLRequest {
    private String query;
    private Map<String, Object> variables;
    private String operationName;
}
