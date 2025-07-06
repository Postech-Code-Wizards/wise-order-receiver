package br.com.wise.orderreceiver.gateway.openfeign.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ClientResponse {

    private String id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

}