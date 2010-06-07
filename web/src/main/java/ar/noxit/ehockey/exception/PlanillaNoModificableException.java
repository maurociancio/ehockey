package ar.noxit.ehockey.exception;

public class PlanillaNoModificableException extends ReglaNegocioException {

    /**
     * {@inheritDoc}
     */
    public PlanillaNoModificableException() {
    }

    /**
     * {@inheritDoc}
     */
    public PlanillaNoModificableException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public PlanillaNoModificableException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public PlanillaNoModificableException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getDescripcion() {
        return "La planilla no se puede modificar";
    }
}
