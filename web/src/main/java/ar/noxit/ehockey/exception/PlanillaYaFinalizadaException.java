package ar.noxit.ehockey.exception;

public class PlanillaYaFinalizadaException extends ReglaNegocioException {

    public PlanillaYaFinalizadaException() {
        super();
    }

    public PlanillaYaFinalizadaException(String message) {
        super(message);
    }

    public PlanillaYaFinalizadaException(Throwable cause) {
        super(cause);
    }

    public PlanillaYaFinalizadaException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getDescripcion() {
        return "La planilla ya está finalizada";
    }
}
