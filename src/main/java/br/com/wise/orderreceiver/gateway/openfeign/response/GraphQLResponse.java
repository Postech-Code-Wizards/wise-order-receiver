package br.com.wise.orderreceiver.gateway.openfeign.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GraphQLResponse {

    private Map<String, Object> data;
    private List<Map<String, Object>> errors;
    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}