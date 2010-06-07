package ar.noxit.ehockey.exception;

/**
 * Lanzada cuando la fecha de inicio del partido es invalida
 * 
 * @author Mauro Ciancio
 * 
 */
public class FechaInvalidaException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public FechaInvalidaException() {
    }

    /**
     * {@inheritDoc}
     */
    public FechaInvalidaException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public FechaInvalidaException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public FechaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getDescripcion() {
        return "Fecha Inválida";
    }
}
