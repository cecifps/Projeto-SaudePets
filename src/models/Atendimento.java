package models;

import java.time.LocalDateTime;
import exceptions.Validacoes;

public class Atendimento {

    private int id;

    private LocalDateTime data;

    private String observacoes;

    private String diagnostico;

    private String tratamento;

    public Atendimento(int id, LocalDateTime data, String observacoes, String diagnostico, String tratamento) {

        this.id = Validacoes.positivo(id, "id");
        this.data = Validacoes.obrigatorio(data, "data do atendimento");
        this.observacoes = Validacoes.texto(observacoes, "observações");
        this.diagnostico = Validacoes.texto(diagnostico, "diagnóstico");
        this.tratamento = Validacoes.texto(tratamento, "tratamento");

    }

    public void registrar() {}

}
