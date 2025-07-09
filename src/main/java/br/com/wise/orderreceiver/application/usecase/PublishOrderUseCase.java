package br.com.wise.orderreceiver.application.usecase;

import br.com.wise.orderreceiver.domain.Order;
import br.com.wise.orderreceiver.gateway.SendOrderGateway;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublishOrderUseCase {

    private final SendOrderGateway sendOrderGateway;

    public void execute(Order order) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); //
            String jsonString = objectMapper.writeValueAsString(order);
            sendOrderGateway.send(jsonString);
        } catch (JsonProcessingException e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.name()
                    , HttpStatus.BAD_REQUEST
                    , "Error converting order to JSON: " + e.getMessage());
        }

    }
}
