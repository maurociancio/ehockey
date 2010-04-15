package ar.noxit.ehockey.exception;

/**
 * Lanzada cuando el club de un jugador no coincide con el club de un equipo de
 * la lista de buena fe
 * 
 * @author Mauro Ciancio
 * 
 */
public class ClubNoCoincideException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public ClubNoCoincideException() {
    }

    /**
     * {@inheritDoc}
     */
    public ClubNoCoincideException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ClubNoCoincideException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public ClubNoCoincideException(String message, Throwable cause) {
        super(message, cause);
    }

}
