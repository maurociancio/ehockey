package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

public class JugadorExistenteException extends NoxitException {

    /**
     * {@inheritDoc}
     */
    public JugadorExistenteException() {
    }

    /**
     * {@inheritDoc}
     */
    public JugadorExistenteException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public JugadorExistenteException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public JugadorExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

}
