package models;

import java.time.LocalDateTime;

public class Atendimento {

    private int id;

    private LocalDateTime data;

    private String observacoes;

    private String diagnostico;

    private String tratamento;

    public Atendimento(int id, LocalDateTime data, String observacoes, String diagnostico, String tratamento) {

        this.id = id;

        this.data = data;

        this.observacoes = observacoes;

        this.diagnostico = diagnostico;

        this.tratamento = tratamento;

    }

    public void registrar() {}

}
