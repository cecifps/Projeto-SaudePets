package models;

import java.time.LocalDate;

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

        this.id = id;

        this.nome = nome;

        this.dataNascimento = dataNascimento;

        this.especie = especie;

        this.raca = raca;

        this.prontuario = new Prontuario(id); 

    }

    public Prontuario consultarProntuario() {

        return this.prontuario;

    }

}
