package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class PlanillaPrecargada extends PlanillaBase {

    public PlanillaPrecargada(Partido partido) {
        super(partido);
        try {
            this.finalizarPlanilla();
        } catch (PlanillaYaFinalizadaException e) {
            throw new NoxitRuntimeException("La planilla precargada ya estaba finalizada");
        }
    }

    protected PlanillaPrecargada() {
    }
}
