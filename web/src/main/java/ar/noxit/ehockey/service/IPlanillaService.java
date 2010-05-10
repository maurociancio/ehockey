package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.PlanillaBase;
import ar.noxit.ehockey.web.pages.planilla.EquipoInfo;
import ar.noxit.exceptions.NoxitException;

public interface IPlanillaService {

    public PlanillaBase get(Integer idPartido) throws NoxitException;

    public void updatePlanilla(int idPartido, Integer golesLocal, Integer golesVisitante, EquipoInfo infoLocal,
            EquipoInfo infoVisitante) throws NoxitException;

    public void finalizarPlanilla(Integer idPartido) throws NoxitException;
}
