package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;
import ar.noxit.ehockey.exception.TransicionEstadoInvalidaException;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;

public class EstadoPlanillaVencida extends EstadoPlanilla {

    @Override
    public EstadoPlanilla publicar(PlanillaPublicable publicable) throws ReglaNegocioException {
        throw new TransicionEstadoInvalidaException("la planilla no puede ser publicada");
    }

    @Override
    public EstadoPlanilla rechazar(Comentable comentable, String mensaje) throws ReglaNegocioException {
        throw new TransicionEstadoInvalidaException("la planilla no puede ser rechazada");
    }

    @Override
    public String toString() {
        return "Vencida (esperando cierre por Federación)";
    }

    @Override
    public EstadoPlanilla validar(PlanillaFinalizable finalizable) throws ReglaNegocioException {
        throw new TransicionEstadoInvalidaException("la planilla no puede ser validada");
    }

    @Override
    public EstadoPlanilla verificarVencimiento(PlanillaVencible vencible, LocalDateTime now) {
        return this;
    }

    @Override
    public EstadoPlanilla cerrarPlanillaVencida(PlanillaPublicable publicable, PlanillaFinalizable finalizable) throws ReglaNegocioException {
        Validate.notNull(publicable);
        publicable.checkPublicable();
        finalizable.finalizarPlanilla();

        return new EstadoPlanillaFinalizada();
    }

    @Override
    public boolean estaVencida() {
        return true;
    }
}
