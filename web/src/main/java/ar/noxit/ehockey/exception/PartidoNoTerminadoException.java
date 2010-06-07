package ar.noxit.ehockey.exception;

public class PartidoNoTerminadoException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public PartidoNoTerminadoException() {
    }

    /**
     * {@inheritDoc}
     */
    public PartidoNoTerminadoException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public PartidoNoTerminadoException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public PartidoNoTerminadoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getDescripcion() {
        return "El partido no ha terminado";
    }
}
