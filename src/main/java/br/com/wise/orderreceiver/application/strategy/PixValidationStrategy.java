package br.com.wise.orderreceiver.application.strategy;

import br.com.wise.orderreceiver.domain.PaymentMethod;
import br.com.wise.orderreceiver.domain.enums.PaymentMethodTypeEnum;
import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class PixValidationStrategy implements PaymentValidationStrategy {

    @Override
    public void validate(PaymentMethod paymentMethod) {
        log.info("Starting validation for PIX.");
        if (StringUtils.hasText(paymentMethod.getCardNumber())
                || StringUtils.hasText(paymentMethod.getCardExpiryDate())
                || StringUtils.hasText(paymentMethod.getCardCvv())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.name()
                    , HttpStatus.BAD_REQUEST
                    , "PIX payment method should not contain card details.");
        }
        log.info("PIX validation completed successfully.");
    }

    @Override
    public PaymentMethodTypeEnum getType() {
        return PaymentMethodTypeEnum.PIX;
    }
}
