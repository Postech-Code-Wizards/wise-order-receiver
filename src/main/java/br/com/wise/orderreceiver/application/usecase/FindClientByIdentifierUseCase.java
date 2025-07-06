package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.gateway.ClientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindClientByIdentifierUseCase {

    private final ClientGateway clientGateway;

    public Client execute(String identifier){
        return clientGateway.getClientByIdentifier(identifier);
    }
}
