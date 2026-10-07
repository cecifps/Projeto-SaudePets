package models;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Profissional extends Usuario {
    private String especialidade;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private List<LocalDate> diasFolga;

    public Profissional(int id, String nome, String email, String senha, String especialidade, LocalTime horarioInicio, LocalTime horarioFim) {
        super(id, nome, email, senha);
        this.especialidade = especialidade;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.diasFolga = new ArrayList<>();
    }

    public List<Agendamento> consultarAgenda() {
        return new ArrayList<>(); 
    }

    public void registrarAtendimento(Atendimento atendimento) {}

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public LocalTime getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(LocalTime horarioInicio) { this.horarioInicio = horarioInicio; }
    public LocalTime getHorarioFim() { return horarioFim; }
    public void setHorarioFim(LocalTime horarioFim) { this.horarioFim = horarioFim; }
    public List<LocalDate> getDiasFolga() { return diasFolga; }
    public void setDiasFolga(List<LocalDate> diasFolga) { this.diasFolga = diasFolga; }
}