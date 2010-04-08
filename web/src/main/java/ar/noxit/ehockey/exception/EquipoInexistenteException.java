package ar.noxit.ehockey.exception;

public class EquipoInexistenteException extends ReglaNegocioException {

    public EquipoInexistenteException() {
    }

    public EquipoInexistenteException(String message) {
        super(message);
    }

    public EquipoInexistenteException(Throwable cause) {
        super(cause);
    }

    public EquipoInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
