package br.com.wise.orderreceiver.gateway;

import br.com.wise.orderreceiver.domain.Client;

public interface ClientGateway {

    Client getClientByIdentifier(String clientIdentifier);

}