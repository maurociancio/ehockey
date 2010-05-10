package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitRuntimeException;

public class JugadorInactivoInmutableException extends NoxitRuntimeException {

    public JugadorInactivoInmutableException() {
        super();
    }

    public JugadorInactivoInmutableException(String message, Throwable cause) {
        super(message, cause);
    }

    public JugadorInactivoInmutableException(String message) {
        super(message);
    }

    public JugadorInactivoInmutableException(Throwable cause) {
        super(cause);
    }

}
