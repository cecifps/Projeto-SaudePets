package services;

import models.Agendamento;
import models.Servico;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArquivoAgendamentoRepositoryTest {
    @TempDir Path pastaTemporaria;

    @Test
    void salvaECarregaAgendamentoPreservandoCaracteresEspeciais() {
        Path arquivo = pastaTemporaria.resolve("dados/agendamentos.csv");
        ArquivoAgendamentoRepository repository = new ArquivoAgendamentoRepository(arquivo);
        Agendamento original = new Agendamento(7, LocalDate.of(2026, 12, 1), LocalTime.of(14, 30),
                "Confirmado", new Servico(3, "Consulta; retorno", "Revisão % clínica", 45));

        repository.salvar(List.of(original));
        List<Agendamento> carregados = repository.carregar();

        assertEquals(1, carregados.size());
        assertEquals(original.getId(), carregados.get(0).getId());
        assertEquals("Consulta; retorno", carregados.get(0).getServico().getNome());
        assertEquals("Revisão % clínica", carregados.get(0).getServico().getDescricao());
    }

    @Test
    void arquivoAusenteRetornaListaVazia() {
        assertTrue(new ArquivoAgendamentoRepository(pastaTemporaria.resolve("inexistente.csv")).carregar().isEmpty());
    }
}
