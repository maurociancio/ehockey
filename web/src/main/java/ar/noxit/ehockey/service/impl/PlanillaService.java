package ar.noxit.ehockey.service.impl;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.dao.IPlanillaDao;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.ehockey.web.pages.planilla.EquipoInfo;
import ar.noxit.exceptions.NoxitException;

public class PlanillaService implements IPlanillaService {

    IPlanillaDao planillaDao;
    IJugadorDao jugadorDao;

    @Override
    @Transactional(readOnly = true)
    public Planilla get(Integer id) throws NoxitException {
        return planillaDao.get(id);
    }

    public void setPlanillaDao(IPlanillaDao planillaDao) {
        this.planillaDao = planillaDao;
    }

    public void setJugadorDao(IJugadorDao jugadorDao) {
        this.jugadorDao = jugadorDao;
    }

    @Override
    @Transactional(readOnly = true)
    public void updatePlanilla(int id, Integer golesLocal, Integer golesVisitante, EquipoInfo infoLocal,
            EquipoInfo infoVisitante) throws NoxitException {
        Planilla planilla = this.planillaDao.get(id);
        planilla.setGolesLocal(golesLocal);
        planilla.setGolesVisitante(golesVisitante);
        planilla.setArbitroL(infoLocal.getArbitro());
        planilla.setDtL(infoLocal.getDt());
        planilla.setGoleadoresL(infoLocal.getGoleadores());
        planilla.setJuezMesaL(infoLocal.getJuezMesa());
        planilla.setMedicoL(infoLocal.getMedico());
        planilla.setPfL(infoLocal.getPf());
        planilla.setArbitroV(infoVisitante.getArbitro());
        planilla.setDtV(infoVisitante.getDt());
        planilla.setGoleadoresV(infoVisitante.getGoleadores());
        planilla.setJuezMesaV(infoVisitante.getJuezMesa());
        planilla.setMedicoV(infoVisitante.getMedico());
        planilla.setPfV(infoVisitante.getPf());

        planilla.getJugadoresL().clear();
        for (Integer jug : infoLocal.getSeleccionados()) {
            planilla.agregarJugadorLocal(jugadorDao.get(jug));
        }

        planilla.getJugadoresV().clear();
        for (Integer jug : infoVisitante.getSeleccionados()) {
            planilla.agregarJugadorLocal(jugadorDao.get(jug));
        }
    }
}
