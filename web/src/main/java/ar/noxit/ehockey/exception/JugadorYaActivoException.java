package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

public class JugadorYaActivoException extends NoxitException {

    /**
     * {@inheritDoc}
     */
    public JugadorYaActivoException() {
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaActivoException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaActivoException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaActivoException(String message, Throwable cause) {
        super(message, cause);
    }
}
