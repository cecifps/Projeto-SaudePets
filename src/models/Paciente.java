package models;

import java.time.LocalDate;
import exceptions.Validacoes;

public class Paciente {

    private int id;

    private String nome;

    private LocalDate dataNascimento;

    private String especie;

    private String raca;

    private String historicoAlergico;

    private String contatoEmergencia;

    private Prontuario prontuario;

    public Paciente(int id, String nome, LocalDate dataNascimento, String especie, String raca) {

        this.id = Validacoes.positivo(id, "id");
        this.nome = Validacoes.texto(nome, "nome");
        this.dataNascimento = Validacoes.obrigatorio(dataNascimento, "data de nascimento");
        if (dataNascimento.isAfter(LocalDate.now())) throw new exceptions.ValidacaoException("A data de nascimento não pode estar no futuro.");
        this.especie = Validacoes.texto(especie, "espécie");
        this.raca = Validacoes.texto(raca, "raça");

        this.prontuario = new Prontuario(id); 

    }

    public Prontuario consultarProntuario() {

        return this.prontuario;

    }

}
