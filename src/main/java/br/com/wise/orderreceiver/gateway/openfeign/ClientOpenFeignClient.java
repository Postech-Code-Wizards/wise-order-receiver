package br.com.wise.orderreceiver.gateway.openfeign;

import br.com.wise.orderreceiver.gateway.openfeign.response.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-api", url = "${client.service.url}")
public interface ClientOpenFeignClient {

    @GetMapping("/cpf/{cpf}")
    ClientResponse getClientByIdentifier(@PathVariable("cpf") String identifier);
}
