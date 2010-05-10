package ar.noxit.ehockey.exception;

public class NoHayPartidoSiguienteException extends ReglaNegocioException {

    public NoHayPartidoSiguienteException() {
        super();
    }

    public NoHayPartidoSiguienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoHayPartidoSiguienteException(String message) {
        super(message);
    }

    public NoHayPartidoSiguienteException(Throwable cause) {
        super(cause);
    }
}
