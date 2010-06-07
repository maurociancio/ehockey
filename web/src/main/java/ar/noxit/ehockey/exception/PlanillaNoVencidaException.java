package ar.noxit.ehockey.exception;

public class PlanillaNoVencidaException extends ReglaNegocioException {

    public PlanillaNoVencidaException() {
    }

    public PlanillaNoVencidaException(String message) {
        super(message);
    }

    public PlanillaNoVencidaException(Throwable cause) {
        super(cause);
    }

    public PlanillaNoVencidaException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getDescripcion() {
        return "La planilla no está vencida";
    }
}
