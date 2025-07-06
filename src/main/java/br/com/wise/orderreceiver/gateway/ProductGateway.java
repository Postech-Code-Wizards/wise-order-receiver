package br.com.wise.orderreceiver.gateway;

import br.com.wise.orderreceiver.domain.Product;

public interface ProductGateway {

    Product getProductBySKU(String sku);

}