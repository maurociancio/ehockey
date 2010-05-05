package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.web.pages.planilla.EquipoInfo;
import ar.noxit.exceptions.NoxitException;

public interface IPlanillaService {

    public Planilla get(Integer id) throws NoxitException;

    public void updatePlanilla(int id, Integer golesLocal, Integer golesVisitante, EquipoInfo infoLocal,
            EquipoInfo infoVisitante) throws NoxitException;

}
