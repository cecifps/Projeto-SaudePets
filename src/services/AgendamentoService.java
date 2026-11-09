package services;

import exceptions.AgendamentoException;
import exceptions.Validacoes;
import models.Agendamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Gerencia agendamentos em memória e evita IDs e horários duplicados. */
public class AgendamentoService {
    private final Map<Integer, Agendamento> agendamentos = new HashMap<>();
    private final Set<String> horariosOcupados = new HashSet<>();

    public void cadastrar(Agendamento agendamento) {
        Validacoes.obrigatorio(agendamento, "agendamento");
        if (agendamentos.containsKey(agendamento.getId()))
            throw new AgendamentoException("Já existe um agendamento com o ID " + agendamento.getId() + ".");
        String horario = chaveHorario(agendamento);
        if (horariosOcupados.contains(horario))
            throw new AgendamentoException("Já existe um agendamento para essa data e horário.");
        agendamentos.put(agendamento.getId(), agendamento);
        if (!"Cancelado".equalsIgnoreCase(agendamento.getStatus())) horariosOcupados.add(horario);
    }

    public Agendamento buscar(int id) {
        Agendamento agendamento = agendamentos.get(id);
        if (agendamento == null) throw new AgendamentoException("Agendamento com ID " + id + " não encontrado.");
        return agendamento;
    }

    public List<Agendamento> listar() { return new ArrayList<>(agendamentos.values()); }

    public void cancelar(int id) {
        Agendamento agendamento = buscar(id);
        agendamento.cancelar();
        horariosOcupados.remove(chaveHorario(agendamento));
    }

    private String chaveHorario(Agendamento agendamento) {
        return agendamento.getData() + "T" + agendamento.getHorario();
    }
}
