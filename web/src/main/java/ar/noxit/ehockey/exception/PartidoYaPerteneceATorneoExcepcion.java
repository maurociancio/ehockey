package ar.noxit.ehockey.exception;

/**
 * Lanzada cuando el partido ya se encuentra agregado al torneo
 * 
 * @author Mauro Ciancio
 * 
 */
public class PartidoYaPerteneceATorneoExcepcion extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public PartidoYaPerteneceATorneoExcepcion() {
    }

    /**
     * {@inheritDoc}
     */
    public PartidoYaPerteneceATorneoExcepcion(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public PartidoYaPerteneceATorneoExcepcion(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */

    public PartidoYaPerteneceATorneoExcepcion(String message, Throwable cause) {
        super(message, cause);
    }

}
