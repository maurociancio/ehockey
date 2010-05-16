package ar.noxit.ehockey.exception;

public class PlanillaIncompletaException extends ReglaNegocioException {

    public PlanillaIncompletaException() {
    }

    public PlanillaIncompletaException(String message) {
        super(message);
    }

    public PlanillaIncompletaException(Throwable cause) {
        super(cause);
    }

    public PlanillaIncompletaException(String message, Throwable cause) {
        super(message, cause);
    }
}
