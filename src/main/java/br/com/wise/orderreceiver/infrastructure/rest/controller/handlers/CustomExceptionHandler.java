package br.com.wise.orderreceiver.infrastructure.rest.controller.handlers;

import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorResponse.ApiError> apiErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    var defaultMessage = error.getDefaultMessage();
                    return new ErrorResponse.ApiError("VALIDATION_ERROR", defaultMessage);
                })
                .toList();

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors));
    }


    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        final String errorCode = ex.getCode();
        final HttpStatus status = ex.getStatus();
        final String message = ex.getMessage();
        final ErrorResponse errorResponse = ErrorResponse.of(status, new ErrorResponse.ApiError(errorCode, message));
        return ResponseEntity.status(status).body(errorResponse);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerInternalServerError(Exception ex) {
        final String errorCode = "INTERNAL_SERVER_ERROR";
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.of(status, new ErrorResponse.ApiError(errorCode, ex.getMessage()));
        return ResponseEntity.status(status).body(errorResponse);
    }

}
