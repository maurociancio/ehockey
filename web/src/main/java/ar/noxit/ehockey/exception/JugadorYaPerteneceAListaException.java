package ar.noxit.ehockey.exception;

/**
 * Excepción lanzada cuando un jugador se agrega dos veces a la misma lista de
 * buena fe.
 * 
 * @author Mauro Ciancio
 * 
 */
public class JugadorYaPerteneceAListaException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public JugadorYaPerteneceAListaException() {
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaPerteneceAListaException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaPerteneceAListaException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public JugadorYaPerteneceAListaException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getDescripcion() {
        return "El jugador ya se encuentra en la lista de buena fe";
    }
}
