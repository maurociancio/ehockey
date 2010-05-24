package ar.noxit.ehockey.exception;

public class PlanillaNoDisponibleException extends ReglaNegocioException {

    public PlanillaNoDisponibleException() {
    }

    public PlanillaNoDisponibleException(String message) {
        super(message);
    }

    public PlanillaNoDisponibleException(Throwable cause) {
        super(cause);
    }

    public PlanillaNoDisponibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
