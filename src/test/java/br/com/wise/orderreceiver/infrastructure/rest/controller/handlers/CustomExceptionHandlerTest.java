package br.com.wise.orderreceiver.infrastructure.rest.controller.handlers;

import br.com.wise.orderreceiver.infrastructure.rest.controller.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomExceptionHandlerTest {

    private final CustomExceptionHandler handler = new CustomExceptionHandler();

    @Test
    @DisplayName("Should return BAD_REQUEST for MethodArgumentNotValidException")
    void testHandleMethodArgumentNotValidException() {
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getDefaultMessage()).thenReturn("error");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ErrorResponse> response = handler.handleMethodArgumentNotValidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return status from BusinessException")
    void testHandleBusinessException() {
        BusinessException ex = mock(BusinessException.class);
        when(ex.getCode()).thenReturn("CODE");
        when(ex.getStatus()).thenReturn(HttpStatus.CONFLICT);
        when(ex.getMessage()).thenReturn("msg");

        ResponseEntity<ErrorResponse> response = handler.handleBusinessException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return BAD_REQUEST for IllegalArgumentException")
    void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("illegal");

        ResponseEntity<String> response = handler.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("illegal", response.getBody());
    }

    @Test
    @DisplayName("Should return INTERNAL_SERVER_ERROR for generic Exception")
    void testHandlerInternalServerError() {
        Exception ex = new Exception("fail");

        ResponseEntity<ErrorResponse> response = handler.handlerInternalServerError(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}