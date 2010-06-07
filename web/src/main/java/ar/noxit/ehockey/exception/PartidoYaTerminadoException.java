package ar.noxit.ehockey.exception;

/**
 * Lanzada cuando el partido ya está finalizado y se intenta finalizar
 * nuevamente
 * 
 * @author Mauro Ciancio
 * 
 */
public class PartidoYaTerminadoException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public PartidoYaTerminadoException() {
    }

    /**
     * {@inheritDoc}
     */
    public PartidoYaTerminadoException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public PartidoYaTerminadoException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public PartidoYaTerminadoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getDescripcion() {
        return "El partido ya está terminado";
    }
}
