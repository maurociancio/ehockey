package ar.noxit.ehockey.exception;

/**
 * Lanzada cuando hay algun error de validacion en los equipos de un partido (ej
 * mismo local y visitante)
 * 
 * @author Mauro Ciancio
 * 
 */
public class EquiposInvalidosException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public EquiposInvalidosException() {
    }

    /**
     * {@inheritDoc}
     */
    public EquiposInvalidosException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public EquiposInvalidosException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public EquiposInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }

}
