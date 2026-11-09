package services;

import exceptions.PersistenciaException;
import exceptions.Validacoes;
import models.Agendamento;
import models.Servico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/** Persistência simples de agendamentos em CSV separado por ponto e vírgula. */
public class ArquivoAgendamentoRepository {
    private final Path arquivo;

    public ArquivoAgendamentoRepository(Path arquivo) {
        this.arquivo = Validacoes.obrigatorio(arquivo, "caminho do arquivo");
    }

    public void salvar(List<Agendamento> agendamentos) {
        Validacoes.obrigatorio(agendamentos, "lista de agendamentos");
        try {
            Path pasta = arquivo.toAbsolutePath().getParent();
            if (pasta != null) Files.createDirectories(pasta);
            try (BufferedWriter writer = Files.newBufferedWriter(arquivo, StandardCharsets.UTF_8)) {
                writer.write("id;data;horario;status;servicoId;servicoNome;servicoDescricao;duracaoMinutos");
                writer.newLine();
                for (Agendamento a : agendamentos) {
                    Validacoes.obrigatorio(a, "agendamento");
                    Servico s = a.getServico();
                    writer.write(String.join(";", Integer.toString(a.getId()), a.getData().toString(),
                            a.getHorario().toString(), codificar(a.getStatus()), Integer.toString(s.getId()),
                            codificar(s.getNome()), codificar(s.getDescricao()), Integer.toString(s.getDuracaoMinutos())));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível salvar os agendamentos em '" + arquivo + "'.", e);
        }
    }

    public List<Agendamento> carregar() {
        if (!Files.exists(arquivo)) return new ArrayList<>();
        List<Agendamento> resultado = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            String linha = reader.readLine(); // cabeçalho
            int numeroLinha = 1;
            while ((linha = reader.readLine()) != null) {
                numeroLinha++;
                String[] campos = linha.split(";", -1);
                if (campos.length != 8) throw new IllegalArgumentException("quantidade de colunas inválida");
                Servico servico = new Servico(Integer.parseInt(campos[4]), decodificar(campos[5]),
                        decodificar(campos[6]), Integer.parseInt(campos[7]));
                resultado.add(new Agendamento(Integer.parseInt(campos[0]), LocalDate.parse(campos[1]),
                        LocalTime.parse(campos[2]), decodificar(campos[3]), servico));
            }
        } catch (IOException | RuntimeException e) {
            throw new PersistenciaException("Não foi possível carregar os agendamentos de '" + arquivo
                    + "'. Verifique o arquivo (linha " + (resultado.size() + 2) + ").", e);
        }
        return resultado;
    }

    private String codificar(String valor) { return valor.replace("%", "%25").replace(";", "%3B").replace("\n", "%0A").replace("\r", "%0D"); }
    private String decodificar(String valor) { return valor.replace("%0D", "\r").replace("%0A", "\n").replace("%3B", ";").replace("%25", "%"); }
}
