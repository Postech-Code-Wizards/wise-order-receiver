package br.com.wise.orderreceiver.infrastructure.rest.controller.handlers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static lombok.AccessLevel.PRIVATE;

@JsonAutoDetect(fieldVisibility = ANY)
@AllArgsConstructor(access = PRIVATE)
public class ErrorResponse {

    private final int statusCode;
    private final List<ApiError> errors;

    static ErrorResponse of(HttpStatus status, List<ApiError> errors) {
        return new ErrorResponse(status.value(), errors);
    }

    static ErrorResponse of(HttpStatus status, ApiError error) {
        return of(status, Collections.singletonList(error));
    }

    @JsonAutoDetect(fieldVisibility = ANY)
    record ApiError(String code, String message) {
    }
}