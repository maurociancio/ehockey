package ar.noxit.ehockey.exception;

public class SinPartidosException extends ReglaNegocioException {

    public SinPartidosException() {
        super();
    }

    public SinPartidosException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinPartidosException(String message) {
        super(message);
    }

    public SinPartidosException(Throwable cause) {
        super(cause);
    }
}
