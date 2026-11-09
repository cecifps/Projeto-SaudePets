package models;

import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import exceptions.Validacoes;

public class Prontuario {

    private int id;

    private List<Atendimento> historico;

    public Prontuario(int id) {

        this.id = Validacoes.positivo(id, "id do prontuário");

        this.historico = new ArrayList<>();

    }

    public void adicionarAtendimento(Atendimento atendimento) {

        this.historico.add(Validacoes.obrigatorio(atendimento, "atendimento"));

    }

    public List<Atendimento> consultarHistorico() {

        return Collections.unmodifiableList(this.historico);

    }

}
