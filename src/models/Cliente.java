package models;

import java.util.List;
import exceptions.Validacoes;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(int id, String nome, String telefone, String email) {
        this.id = Validacoes.positivo(id, "id");
        this.nome = Validacoes.texto(nome, "nome");
        this.telefone = Validacoes.texto(telefone, "telefone");
        this.email = Validacoes.texto(email, "email");
    }

    public List<Atendimento> consultarHistorico(Paciente paciente) {
        return Validacoes.obrigatorio(paciente, "paciente").consultarProntuario().consultarHistorico();
    }
}
