package ar.noxit.ehockey.exception;

/**
 * Lanzada cuando se intenta agregar un partido a un torneo y el partido posee
 * asignado otro torneo.
 * 
 * @author Mauro Ciancio
 * 
 */
public class TorneoNoCoincideException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public TorneoNoCoincideException() {
    }

    /**
     * {@inheritDoc}
     */
    public TorneoNoCoincideException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public TorneoNoCoincideException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public TorneoNoCoincideException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getDescripcion() {
        return "El partido no coincide con el torneo";
    }
}
