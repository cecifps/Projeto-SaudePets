package models;

public class Servico {
    private int id;
    private String nome;
    private String descricao;
    private int duracaoMinutos;

    public Servico(int id, String nome, String descricao, int duracaoMinutos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.duracaoMinutos = duracaoMinutos;
    }
}