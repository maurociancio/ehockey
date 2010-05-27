package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaNoVencidaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import ar.noxit.ehockey.exception.TransicionEstadoInvalidaException;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EstadoPlanilla {

    private static final Logger logger = LoggerFactory.getLogger(EstadoPlanilla.class);

    public abstract EstadoPlanilla publicar(PlanillaPublicable publicable) throws ReglaNegocioException;

    public abstract EstadoPlanilla rechazar(Comentable comentable, String mensaje) throws ReglaNegocioException;

    public abstract EstadoPlanilla validar(PlanillaFinalizable finalizable) throws ReglaNegocioException;

    public abstract EstadoPlanilla verificarVencimiento(PlanillaVencible vencible, LocalDateTime now);

    public EstadoPlanilla cerrarPlanillaVencida(PlanillaPublicable publicable, PlanillaFinalizable finalizable) throws ReglaNegocioException {
        throw new TransicionEstadoInvalidaException("la planilla no puede ser cerrada");
    }

    protected EstadoPlanilla vencerPlanillaCargada(PlanillaVencible vencible, LocalDateTime now) {
        Validate.notNull(vencible);
        Validate.notNull(now);

        try {
            // vemos si esta vencida o no
            vencible.checkVencimiento(now);

            // TODO
            // colocar sancion al club local

            // la planilla está vencida
            return new EstadoPlanillaVencida();
        } catch (PlanillaNoVencidaException e) {
            logger.debug("planilla no vencida", e);
            return this;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        return this.getClass().equals(obj.getClass());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    @Override
    public abstract String toString();

    public boolean esEditable() {
        return false;
    }

    public boolean estaPublicada() {
        return false;
    }

    public boolean estaRechazada() {
        return false;
    }

    public boolean estaFinalizada() {
        return false;
    }

    public boolean estaVencida() {
        return false;
    }
}
