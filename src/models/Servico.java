package models;

import exceptions.Validacoes;

public class Servico {
    private int id;
    private String nome;
    private String descricao;
    private int duracaoMinutos;

    public Servico(int id, String nome, String descricao, int duracaoMinutos) {
        this.id = Validacoes.positivo(id, "id");
        this.nome = Validacoes.texto(nome, "nome do serviço");
        this.descricao = Validacoes.texto(descricao, "descrição");
        this.duracaoMinutos = Validacoes.positivo(duracaoMinutos, "duração em minutos");
    }
}
