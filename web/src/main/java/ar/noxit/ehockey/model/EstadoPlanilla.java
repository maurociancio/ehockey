package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;

public abstract class EstadoPlanilla {

    public abstract EstadoPlanilla publicar(PlanillaPublicable publicable) throws ReglaNegocioException;

    public abstract EstadoPlanilla rechazar(Comentable comentable, String mensaje) throws ReglaNegocioException;

    public abstract EstadoPlanilla validar(PlanillaFinalizable finalizable) throws ReglaNegocioException;

    public boolean estaRechazada() {
        return false;
    }

    public boolean estaFinalizada() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
