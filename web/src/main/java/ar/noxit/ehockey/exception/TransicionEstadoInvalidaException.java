package ar.noxit.ehockey.exception;

public class TransicionEstadoInvalidaException extends ReglaNegocioException {

    public TransicionEstadoInvalidaException() {
    }

    public TransicionEstadoInvalidaException(String message) {
        super(message);
    }

    public TransicionEstadoInvalidaException(Throwable cause) {
        super(cause);
    }

    public TransicionEstadoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
