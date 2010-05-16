package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;

public interface PlanillaFinalizable {

    PlanillaBase finalizarPlanilla() throws PlanillaYaFinalizadaException;
}
