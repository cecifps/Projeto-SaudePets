package models;

public class Administrador extends Usuario {
    
    public Administrador(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    public void cadastrarUsuario(Usuario usuario) {}
    public void removerUsuario(Usuario usuario) {}
    public void definirExpediente(Profissional profissional) {}
}