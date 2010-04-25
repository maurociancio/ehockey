package ar.noxit.ehockey.exception;

public class PartidoNoJugadoPorEquipoException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public PartidoNoJugadoPorEquipoException() {
    }

    /**
     * {@inheritDoc}
     */
    public PartidoNoJugadoPorEquipoException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public PartidoNoJugadoPorEquipoException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public PartidoNoJugadoPorEquipoException(String message, Throwable cause) {
        super(message, cause);
    }
}
