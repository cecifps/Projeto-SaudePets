package models;

import java.time.LocalDate;

import java.time.LocalTime;

public class Agendamento {

    private int id;

    private LocalDate data;

    private LocalTime horario;

    private String status;

    private Servico servico; 

    public Agendamento(int id, LocalDate data, LocalTime horario, String status, Servico servico) {

        this.id = id;

        this.data = data;

        this.horario = horario;

        this.status = status;

        this.servico = servico;

    }

    public void cancelar() { this.status = "Cancelado"; }

    

    public void remarcar(LocalDate data, LocalTime horario) {

        this.data = data;

        this.horario = horario;

        this.status = "Remarcado";

    }

    public boolean verificarConflito() {

        return false;

    }

}
