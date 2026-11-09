package exceptions;

/** Verificações comuns para manter mensagens de validação consistentes. */
public final class Validacoes {
    private Validacoes() {}

    public static <T> T obrigatorio(T valor, String campo) {
        if (valor == null) throw new ValidacaoException("O campo '" + campo + "' é obrigatório.");
        return valor;
    }

    public static String texto(String valor, String campo) {
        if (valor == null || valor.isBlank())
            throw new ValidacaoException("O campo '" + campo + "' não pode ficar vazio.");
        return valor.trim();
    }

    public static int positivo(int valor, String campo) {
        if (valor <= 0) throw new ValidacaoException("O campo '" + campo + "' deve ser maior que zero.");
        return valor;
    }
}
