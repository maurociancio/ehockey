package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.web.pages.planilla.EquipoInfo;
import ar.noxit.exceptions.NoxitException;

public interface IPlanillaService {

    public PlanillaFinal get(Integer idPartido) throws NoxitException;

    public void updatePlanilla(int idPartido, Integer golesLocal, Integer golesVisitante, EquipoInfo infoLocal,
            EquipoInfo infoVisitante) throws NoxitException;

    public void validarPlanilla(Integer idPartido) throws NoxitException;
    
    public void publicarPlanilla(Integer idPartido) throws NoxitException;
    
    public void rechazarPlanilla(Integer idPartido, String comentario) throws NoxitException;
}
