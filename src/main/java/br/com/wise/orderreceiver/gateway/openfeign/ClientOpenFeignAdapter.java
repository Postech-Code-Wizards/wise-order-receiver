package br.com.wise.orderreceiver.gateway.openfeign;

import br.com.wise.orderreceiver.domain.Client;
import br.com.wise.orderreceiver.gateway.ClientGateway;
import br.com.wise.orderreceiver.gateway.openfeign.converter.ClientResponseToClient;
import br.com.wise.orderreceiver.gateway.openfeign.response.ClientResponse;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientOpenFeignAdapter implements ClientGateway {

    private final ClientOpenFeignClient clientOpenFeignClient;
    private final ClientResponseToClient clientResponseToClient;

    @Override
    public Client getClientByIdentifier(String clientIdentifier) {

        ClientResponse clientResponse;
        try {
            clientResponse = clientOpenFeignClient.getClientByIdentifier(clientIdentifier);
        } catch (FeignException.NotFound e){
            throw new BusinessException(HttpStatus.NOT_FOUND.name()
                    , HttpStatus.NOT_FOUND
                    , e.getMessage());
        }
        return clientResponseToClient.convert(clientResponse);
    }

}
