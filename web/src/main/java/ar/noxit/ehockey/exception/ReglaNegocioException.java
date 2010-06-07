package ar.noxit.ehockey.exception;

import ar.noxit.ehockey.service.IReportable;
import ar.noxit.exceptions.NoxitException;

/**
 * Clase base para las Excepciones de Reglas de Negocio
 * 
 * @author Mauro Ciancio
 * 
 */
public class ReglaNegocioException extends NoxitException implements Descriptible {

    private String mensaje = "Violación de Regla de Negocio";

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

    @Override
    public String getDescripcion() {
        return mensaje;
    }

    public ReglaNegocioException setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    @Override
    public void reportar(IReportable reportable) {
        reportable.reportar(getDescripcion());
    }
}
