package ar.noxit.ehockey.model;

import org.joda.time.LocalDateTime;

import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import ar.noxit.ehockey.exception.TransicionEstadoInvalidaException;

public class EstadoPlanillaFinalizada extends EstadoPlanilla {

    @Override
    public boolean estaRechazada() {
        return false;
    }

    @Override
    public EstadoPlanilla publicar(PlanillaPublicable publicable) throws ReglaNegocioException {
        throw new TransicionEstadoInvalidaException("la planilla está finalizada");
    }

    @Override
    public EstadoPlanilla rechazar(Comentable comentable, String mensaje) throws ReglaNegocioException {
        throw new TransicionEstadoInvalidaException("la planilla está finalizada");
    }

    @Override
    public EstadoPlanilla validar(PlanillaFinalizable finalizable) throws ReglaNegocioException {
        throw new PlanillaYaFinalizadaException("la planilla está finalizada");
    }

    @Override
    public boolean estaFinalizada() {
        return true;
    }

    @Override
    public String toString() {
        return "Planilla Validada";
    }

    @Override
    public String toStringReducido() {
        return "Validada";
    }

    @Override
    public EstadoPlanilla verificarVencimiento(PlanillaVencible vencible, LocalDateTime now) {
        return this;
    }
}
