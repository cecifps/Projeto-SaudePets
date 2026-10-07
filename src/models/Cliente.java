package models;

import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public List<Atendimento> consultarHistorico(Paciente paciente) {
        return paciente.consultarProntuario().consultarHistorico();
    }
}
