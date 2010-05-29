package ar.noxit.ehockey.exception;

public class JugadorSinTarjetasException extends ReglaNegocioException {

    public JugadorSinTarjetasException() {
        super();
    }

    public JugadorSinTarjetasException(String message) {
        super(message);
    }

    public JugadorSinTarjetasException(Throwable cause) {
        super(cause);
    }

    public JugadorSinTarjetasException(String message, Throwable cause) {
        super(message, cause);
    }

}
