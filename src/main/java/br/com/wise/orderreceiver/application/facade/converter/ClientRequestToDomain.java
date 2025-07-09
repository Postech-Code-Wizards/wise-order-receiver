package br.com.wise.orderreceiver.application.facade.converter;

import br.com.wise.orderreceiver.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRequestToDomain {

    public Client execute(String identifier) {
        return Client.builder()
                .identifier(identifier)
                .build();
    }
}
