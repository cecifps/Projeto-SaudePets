package models;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import exceptions.Validacoes;

public class Profissional extends Usuario {
    private String especialidade;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private List<LocalDate> diasFolga;

    public Profissional(int id, String nome, String email, String senha, String especialidade, LocalTime horarioInicio, LocalTime horarioFim) {
        super(id, nome, email, senha);
        this.especialidade = Validacoes.texto(especialidade, "especialidade");
        this.horarioInicio = Validacoes.obrigatorio(horarioInicio, "horário de início");
        this.horarioFim = Validacoes.obrigatorio(horarioFim, "horário de término");
        if (!horarioFim.isAfter(horarioInicio))
            throw new exceptions.ValidacaoException("O horário de término deve ser posterior ao horário de início.");
        this.diasFolga = new ArrayList<>();
    }

    public List<Agendamento> consultarAgenda() {
        return new ArrayList<>(); 
    }

    public void registrarAtendimento(Atendimento atendimento) {}

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = Validacoes.texto(especialidade, "especialidade"); }
    public LocalTime getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(LocalTime horarioInicio) {
        LocalTime inicio = Validacoes.obrigatorio(horarioInicio, "horário de início");
        if (horarioFim != null && !horarioFim.isAfter(inicio))
            throw new exceptions.ValidacaoException("O horário de término deve ser posterior ao horário de início.");
        this.horarioInicio = inicio;
    }
    public LocalTime getHorarioFim() { return horarioFim; }
    public void setHorarioFim(LocalTime horarioFim) {
        LocalTime fim = Validacoes.obrigatorio(horarioFim, "horário de término");
        if (horarioInicio != null && !fim.isAfter(horarioInicio))
            throw new exceptions.ValidacaoException("O horário de término deve ser posterior ao horário de início.");
        this.horarioFim = fim;
    }
    public List<LocalDate> getDiasFolga() { return Collections.unmodifiableList(diasFolga); }
    public void setDiasFolga(List<LocalDate> diasFolga) {
        Validacoes.obrigatorio(diasFolga, "dias de folga");
        if (diasFolga.contains(null)) throw new exceptions.ValidacaoException("A lista de dias de folga não pode conter datas vazias.");
        this.diasFolga = new ArrayList<>(diasFolga);
    }
}
