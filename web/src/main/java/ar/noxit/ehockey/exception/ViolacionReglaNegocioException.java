package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitRuntimeException;

/**
 * Lanzada cuando se produce una condición inesperada de la cual el sistema no
 * puede recuperarse
 * 
 * @author Mauro Ciancio
 * 
 */
public class ViolacionReglaNegocioException extends NoxitRuntimeException {

    /**
     * {@inheritDoc}
     */
    public ViolacionReglaNegocioException() {
    }

    /**
     * {@inheritDoc}
     */
    public ViolacionReglaNegocioException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ViolacionReglaNegocioException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public ViolacionReglaNegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}
