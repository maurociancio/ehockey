package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import ar.noxit.ehockey.exception.TransicionEstadoInvalidaException;

public class EstadoPlanillaFinalizada extends EstadoPlanilla {

    @Override
    public Integer getId() {
        return 3;
    }

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
}
