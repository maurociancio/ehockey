package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;
import ar.noxit.ehockey.exception.TransicionEstadoInvalidaException;
import org.apache.commons.lang.Validate;

public class EstadoPlanillaPublicada extends EstadoPlanilla {

    @Override
    public Integer getId() {
        return 2;
    }

    @Override
    public EstadoPlanilla publicar(PlanillaPublicable publicable) throws TransicionEstadoInvalidaException {
        throw new TransicionEstadoInvalidaException("la planilla ya está publicada");
    }

    @Override
    public EstadoPlanilla rechazar(Comentable comentable, String mensaje) throws TransicionEstadoInvalidaException {
        Validate.notNull(comentable);
        Validate.notNull(mensaje);

        comentable.comentar(mensaje);
        return new EstadoPlanillaRechazada();
    }

    @Override
    public EstadoPlanilla validar(PlanillaFinalizable finalizable) throws ReglaNegocioException {
        Validate.notNull(finalizable);

        finalizable.finalizarPlanilla();
        return new EstadoPlanillaFinalizada();
    }
}
