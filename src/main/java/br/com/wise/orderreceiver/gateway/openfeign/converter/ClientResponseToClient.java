package br.com.wise.orderreceiver.gateway.openfeign.converter;

import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.gateway.openfeign.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientResponseToClient {

    public Client convert(ClientResponse clientResponse){
        return Client.builder()
                .id(clientResponse.getId())
                .name(clientResponse.getNome())
                .identifier(clientResponse.getCpf())
                .datOfBirth(clientResponse.getDataNascimento())
                .build();
    }
}
