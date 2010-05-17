package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;

public interface PlanillaFinalizable {

    void finalizarPlanilla() throws ReglaNegocioException;
}
