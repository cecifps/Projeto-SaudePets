package exceptions;

/** Indica que os dados informados para uma operação são inválidos. */
public class ValidacaoException extends IllegalArgumentException {
    public ValidacaoException(String mensagem) { super(mensagem); }
}
