package services;

import exceptions.AgendamentoException;
import models.Agendamento;
import models.Servico;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoServiceTest {
    private Agendamento agendamento(int id, LocalTime horario) {
        return new Agendamento(id, LocalDate.of(2026, 12, 1), horario, "Confirmado",
                new Servico(1, "Consulta", "Consulta clínica", 30));
    }

    @Test
    void cadastraBuscaListaECancelaAgendamento() {
        AgendamentoService service = new AgendamentoService();
        service.cadastrar(agendamento(1, LocalTime.of(9, 0)));

        assertEquals(1, service.buscar(1).getId());
        assertEquals(1, service.listar().size());
        service.cancelar(1);
        assertEquals("Cancelado", service.buscar(1).getStatus());
    }

    @Test
    void rejeitaHorarioDuplicadoEIdDuplicado() {
        AgendamentoService service = new AgendamentoService();
        service.cadastrar(agendamento(1, LocalTime.of(9, 0)));

        assertThrows(AgendamentoException.class, () -> service.cadastrar(agendamento(1, LocalTime.of(10, 0))));
        assertThrows(AgendamentoException.class, () -> service.cadastrar(agendamento(2, LocalTime.of(9, 0))));
    }
}
