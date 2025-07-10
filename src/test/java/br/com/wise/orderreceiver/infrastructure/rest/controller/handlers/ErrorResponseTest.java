package br.com.wise.orderreceiver.infrastructure.rest.controller.handlers;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ErrorResponseTest {

    @Test
    @DisplayName("Should create ErrorResponse with list of ApiError using of(HttpStatus, List<ApiError>)")
    void shouldCreateErrorResponseWithListOfApiError() {
        List<ErrorResponse.ApiError> errors = Instancio.ofList(ErrorResponse.ApiError.class).size(2).create();
        ErrorResponse response = ErrorResponse.of(HttpStatus.BAD_REQUEST, errors);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Should create ErrorResponse with single ApiError using of(HttpStatus, ApiError)")
    void shouldCreateErrorResponseWithSingleApiError() {
        ErrorResponse.ApiError error = Instancio.create(ErrorResponse.ApiError.class);
        ErrorResponse response = ErrorResponse.of(HttpStatus.NOT_FOUND, error);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Should create ApiError with code and message")
    void shouldCreateApiErrorWithCodeAndMessage() {
        String code = "ERROR_CODE";
        String message = "Error message";
        ErrorResponse.ApiError apiError = new ErrorResponse.ApiError(code, message);
        assertNotNull(apiError);
    }
}