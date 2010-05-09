package ar.noxit.ehockey.exception;

public class PlanillaNoFinalizadaException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public PlanillaNoFinalizadaException() {
    }

    /**
     * {@inheritDoc}
     */
    public PlanillaNoFinalizadaException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public PlanillaNoFinalizadaException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public PlanillaNoFinalizadaException(String message, Throwable cause) {
        super(message, cause);
    }
}
