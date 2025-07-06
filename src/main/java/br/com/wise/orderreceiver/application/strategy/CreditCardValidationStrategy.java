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
public class CreditCardValidationStrategy implements PaymentValidationStrategy {

    @Override
    public void validate(PaymentMethod paymentMethod) {
        log.info("Starting validation for Credit Card.");
        validateCardNumber(paymentMethod);
        validateCardExpiryDate(paymentMethod);
        validateCardCVV(paymentMethod);
        log.info("Credit Card Validation completed successfully.");
    }

    private static void validateCardCVV(PaymentMethod paymentMethod) {
        if (!StringUtils.hasText(paymentMethod.getCardCvv()) || paymentMethod.getCardCvv().length() < 3 || paymentMethod.getCardCvv().length() > 4) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.name()
                    , HttpStatus.BAD_REQUEST
                    , "Invalid card CVV.");
        }
    }

    private static void validateCardExpiryDate(PaymentMethod paymentMethod) {
        if (!StringUtils.hasText(paymentMethod.getCardExpiryDate()) || !paymentMethod.getCardExpiryDate().matches("\\d{2}/\\d{2}")) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.name()
                    , HttpStatus.BAD_REQUEST
                    , "Invalid card expiration date. MM/YY format.");
        }
    }

    private static void validateCardNumber(PaymentMethod paymentMethod) {
        if (!StringUtils.hasText(paymentMethod.getCardNumber()) || paymentMethod.getCardNumber().length() < 13 || paymentMethod.getCardNumber().length() > 19) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.name()
                    , HttpStatus.BAD_REQUEST
                    , "Invalid credit card number.");
        }
    }

    @Override
    public PaymentMethodTypeEnum getType() {
        return PaymentMethodTypeEnum.CREDIT_CARD;
    }
}
