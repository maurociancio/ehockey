package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

public class JugadorYaBajaException extends NoxitException {

    /**
     * {@inheritDoc}
     */
    public JugadorYaBajaException() {
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaBajaException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaBajaException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaBajaException(String message, Throwable cause) {
        super(message, cause);
    }

}
