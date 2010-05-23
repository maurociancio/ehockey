package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitRuntimeException;

public class PlanillaNoDisponibleRuntimeException extends NoxitRuntimeException {

    public PlanillaNoDisponibleRuntimeException() {
    }

    public PlanillaNoDisponibleRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlanillaNoDisponibleRuntimeException(String message) {
        super(message);
    }

    public PlanillaNoDisponibleRuntimeException(Throwable cause) {
        super(cause);
    }
}
