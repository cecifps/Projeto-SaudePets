package models;

import java.util.List;

import java.util.ArrayList;

public class Prontuario {

    private int id;

    private List<Atendimento> historico;

    public Prontuario(int id) {

        this.id = id;

        this.historico = new ArrayList<>();

    }

    public void adicionarAtendimento(Atendimento atendimento) {

        this.historico.add(atendimento);

    }

    public List<Atendimento> consultarHistorico() {

        return this.historico;

    }

}
