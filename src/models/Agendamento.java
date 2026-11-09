package models;

import java.time.LocalDate;

import java.time.LocalTime;
import exceptions.Validacoes;
import exceptions.AgendamentoException;

public class Agendamento {

    private int id;

    private LocalDate data;

    private LocalTime horario;

    private String status;

    private Servico servico; 

    public Agendamento(int id, LocalDate data, LocalTime horario, String status, Servico servico) {

        this.id = Validacoes.positivo(id, "id");
        this.data = Validacoes.obrigatorio(data, "data");
        this.horario = Validacoes.obrigatorio(horario, "horário");
        this.status = Validacoes.texto(status, "status");
        this.servico = Validacoes.obrigatorio(servico, "serviço");

    }

    public void cancelar() {
        if ("Cancelado".equalsIgnoreCase(status)) throw new AgendamentoException("Este agendamento já está cancelado.");
        this.status = "Cancelado";
    }

    

    public void remarcar(LocalDate data, LocalTime horario) {

        if ("Cancelado".equalsIgnoreCase(status)) throw new AgendamentoException("Não é possível remarcar um agendamento cancelado.");
        this.data = Validacoes.obrigatorio(data, "nova data");
        this.horario = Validacoes.obrigatorio(horario, "novo horário");
        this.status = "Remarcado";

    }

    public boolean verificarConflito() {

        return false;

    }

    public int getId() { return id; }
    public LocalDate getData() { return data; }
    public LocalTime getHorario() { return horario; }
    public String getStatus() { return status; }
    public Servico getServico() { return servico; }

}
