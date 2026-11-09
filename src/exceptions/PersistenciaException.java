package exceptions;

/** Indica falha ao ler ou gravar os dados persistidos do sistema. */
public class PersistenciaException extends RuntimeException {
    public PersistenciaException(String mensagem, Throwable causa) { super(mensagem, causa); }
}
