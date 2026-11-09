package exceptions;

/** Indica uma operação incompatível com o estado atual do agendamento. */
public class AgendamentoException extends IllegalStateException {
    public AgendamentoException(String mensagem) { super(mensagem); }
}
