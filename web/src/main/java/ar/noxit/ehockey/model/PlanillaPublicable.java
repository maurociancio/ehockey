package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;

public interface PlanillaPublicable {

    void checkPublicable() throws ReglaNegocioException;
}
