package models;

import exceptions.Validacoes;

public abstract class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(int id, String nome, String email, String senha) {
        this.id = Validacoes.positivo(id, "id");
        this.nome = Validacoes.texto(nome, "nome");
        this.email = validarEmail(email);
        this.senha = Validacoes.texto(senha, "senha");
    }

    public boolean login(String email, String senha) {
        return email != null && senha != null && this.email.equalsIgnoreCase(email.trim()) && this.senha.equals(senha);
    }

    public void logout() {
        System.out.println(this.nome + " fez logout.");
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = Validacoes.positivo(id, "id"); }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = Validacoes.texto(nome, "nome"); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = validarEmail(email); }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = Validacoes.texto(senha, "senha"); }

    private static String validarEmail(String email) {
        String valor = Validacoes.texto(email, "email");
        if (!valor.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"))
            throw new exceptions.ValidacaoException("Informe um endereço de e-mail válido.");
        return valor;
    }
}
