package models;

public class Atendente extends Usuario {
    
    public Atendente(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    public void cadastrarCliente(Cliente cliente) {}
    public void cadastrarPaciente(Paciente paciente) {}
    public void cadastrarProfissional(Profissional profissional) {}
    public void criarAgendamento(Agendamento agendamento) {}
    public void cancelarAgendamento(Agendamento agendamento) {}
    public void remarcarAgendamento(Agendamento agendamento) {}
}