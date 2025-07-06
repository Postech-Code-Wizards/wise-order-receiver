package br.com.wise.orderreceiver.gateway.openfeign.response;

import java.util.List;
import java.util.Map;

public class ProductResponse {
    private Map<String, Object> data; // Onde os resultados da query/mutação estarão
    private List<Map<String, Object>> errors; // Onde os erros do GraphQL estarão

    // Getters e Setters
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public List<Map<String, Object>> getErrors() {
        return errors;
    }

    public void setErrors(List<Map<String, Object>> errors) {
        this.errors = errors;
    }
}