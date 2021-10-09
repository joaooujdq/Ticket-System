package com.example.ticket_system.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardError {
    private Integer codigo;
    private LocalDateTime momento;
    private String descricao;
    private List<Fields> campos;


    public static class Fields {
        private String nome;
        private String mensagem;

        @SuppressWarnings("all")
        public Fields(final String nome, final String mensagem) {
            this.nome = nome;
            this.mensagem = mensagem;
        }

        @SuppressWarnings("all")
        public void setNome(final String nome) {
            this.nome = nome;
        }

        @SuppressWarnings("all")
        public void setMensagem(final String mensagem) {
            this.mensagem = mensagem;
        }

        @SuppressWarnings("all")
        public String getNome() {
            return this.nome;
        }

        @SuppressWarnings("all")
        public String getMensagem() {
            return this.mensagem;
        }
    }

    @SuppressWarnings("all")
    public StandardError(final Integer codigo, final LocalDateTime momento, final String descricao, final List<Fields> campos) {
        this.codigo = codigo;
        this.momento = momento;
        this.descricao = descricao;
        this.campos = campos;
    }

    @SuppressWarnings("all")
    public Integer getCodigo() {
        return this.codigo;
    }

    @SuppressWarnings("all")
    public LocalDateTime getMomento() {
        return this.momento;
    }

    @SuppressWarnings("all")
    public String getDescricao() {
        return this.descricao;
    }

    @SuppressWarnings("all")
    public List<Fields> getCampos() {
        return this.campos;
    }

    @SuppressWarnings("all")
    public void setCodigo(final Integer codigo) {
        this.codigo = codigo;
    }

    @SuppressWarnings("all")
    public void setMomento(final LocalDateTime momento) {
        this.momento = momento;
    }

    @SuppressWarnings("all")
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    @SuppressWarnings("all")
    public void setCampos(final List<Fields> campos) {
        this.campos = campos;
    }
}
