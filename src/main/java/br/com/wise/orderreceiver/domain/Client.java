package br.com.wise.orderreceiver.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Client {

    private String id;
    private String name;
    private String identifier;
    private LocalDate datOfBirth;

}
