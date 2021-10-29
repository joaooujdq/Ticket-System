package com.example.ticket_system.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class StandardError {

    private Integer codigo;
    private LocalDateTime momento;
    private String descricao;
    private List<Fields> campos;

    @AllArgsConstructor
    @Setter
    @Getter
    public static class Fields{
        private String nome;
        private String mensagem;
    }
}
