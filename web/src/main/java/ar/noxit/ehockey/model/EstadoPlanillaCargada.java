package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;
import ar.noxit.ehockey.exception.TransicionEstadoInvalidaException;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;

public class EstadoPlanillaCargada extends EstadoPlanilla {

    @Override
    public EstadoPlanilla publicar(PlanillaPublicable publicable) throws ReglaNegocioException {
        Validate.notNull(publicable);

        publicable.checkPublicable();
        return new EstadoPlanillaPublicada();
    }

    @Override
    public EstadoPlanilla rechazar(Comentable comentable, String mensaje) throws TransicionEstadoInvalidaException {
        throw new TransicionEstadoInvalidaException("la planilla no está publicada");
    }

    @Override
    public EstadoPlanilla validar(PlanillaFinalizable finalizable) throws TransicionEstadoInvalidaException {
        throw new TransicionEstadoInvalidaException("la planilla no está publicada");
    }

    @Override
    public String toString() {
        return "Carga de datos parcialmente completa";
    }

    @Override
    public EstadoPlanilla verificarVencimiento(PlanillaVencible vencible, LocalDateTime now) {
        return vencerPlanillaCargada(vencible, now);
    }

    @Override
    public boolean esEditable() {
        return true;
    }

    @Override
    public String toStringReducido() {
        return "Carga Parcial";
    }
}