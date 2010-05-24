package ar.noxit.ehockey.exception;

public class FechaPartidoAunNoSucedidaException extends ReglaNegocioException {

    public FechaPartidoAunNoSucedidaException() {
    }

    public FechaPartidoAunNoSucedidaException(String message) {
        super(message);
    }

    public FechaPartidoAunNoSucedidaException(Throwable cause) {
        super(cause);
    }

    public FechaPartidoAunNoSucedidaException(String message, Throwable cause) {
        super(message, cause);
    }

}
