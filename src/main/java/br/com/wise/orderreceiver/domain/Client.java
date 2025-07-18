package br.com.wise.orderreceiver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Builder
public class Client {

    private BigInteger id;
    private String name;
    private String identifier;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate datOfBirth;

}
