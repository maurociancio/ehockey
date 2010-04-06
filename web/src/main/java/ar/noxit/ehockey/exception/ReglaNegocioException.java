package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

/**
 * Clase base para las Excepciones de Reglas de Negocio
 * 
 * @author Mauro Ciancio
 * 
 */
public class ReglaNegocioException extends NoxitException {

    /**
     * {@inheritDoc}
     */
    public ReglaNegocioException() {
    }

    /**
     * {@inheritDoc}
     */

    public ReglaNegocioException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ReglaNegocioException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public ReglaNegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}
