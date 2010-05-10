package ar.noxit.ehockey.exception;

public class TarjetaYaUsadaException extends ReglaNegocioException {

    public TarjetaYaUsadaException() {
    }

    public TarjetaYaUsadaException(String message) {
        super(message);
    }

    public TarjetaYaUsadaException(Throwable cause) {
        super(cause);
    }

    public TarjetaYaUsadaException(String message, Throwable cause) {
        super(message, cause);
    }
}
