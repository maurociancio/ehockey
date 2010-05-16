package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaNoModificableException;

import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;

public class PlanillaPrecargada extends PlanillaBase {

    public PlanillaPrecargada(Partido partido) {
        super(partido);
    }

    @Override
    public PlanillaBase finalizarPlanilla() throws PlanillaYaFinalizadaException {
        throw new PlanillaYaFinalizadaException();
    }

    @Override
    public boolean isFinalizada() {
        return true;
    }

    protected void validatePlanillaCerrada() throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    protected PlanillaPrecargada() {
    }
}
