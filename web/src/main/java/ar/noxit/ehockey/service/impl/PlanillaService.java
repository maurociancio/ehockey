package ar.noxit.ehockey.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.dao.IPartidoDao;
import ar.noxit.ehockey.model.DatosEquipoPlanilla;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.ehockey.web.pages.planilla.AmonestacionInfo;
import ar.noxit.ehockey.web.pages.planilla.EquipoInfo;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.persistence.PersistenceException;

public class PlanillaService implements IPlanillaService {

    private IPartidoDao partidoDao;
    private IJugadorDao jugadorDao;

    @Override
    @Transactional(readOnly = true)
    public PlanillaFinal get(Integer idPartido) throws NoxitException {
        return partidoDao.get(idPartido).getPlanilla();
    }

    public void setPartidoDao(IPartidoDao partidoDao) {
        this.partidoDao = partidoDao;
    }

    public void setJugadorDao(IJugadorDao jugadorDao) {
        this.jugadorDao = jugadorDao;
    }

    private Collection<Jugador> crearColeccionJugadores(PlanillaFinal planilla, EquipoInfo info) throws NoxitException {
        Collection<Jugador> temp = new ArrayList<Jugador>();
        for (Integer jug : info.getSeleccionados()) {
            temp.add(jugadorDao.get(jug));
        }
        return temp;
    }

    @Override
    @Transactional
    public void updatePlanilla(int idPartido, Integer golesLocal, Integer golesVisitante, EquipoInfo infoLocal,
            EquipoInfo infoVisitante) throws NoxitException {
        PlanillaFinal planilla = this.partidoDao.get(idPartido).getPlanilla();
        planilla.setGolesLocal(golesLocal);
        planilla.setGolesVisitante(golesVisitante);
        planilla.setArbitroL(infoLocal.getArbitro());
        planilla.setDtL(infoLocal.getDt());
        planilla.setGoleadoresL(infoLocal.getGoleadores());
        planilla.setJuezMesaL(infoLocal.getJuezMesa());
        planilla.setCapitanL(infoLocal.getCapitan());
        planilla.setMedicoL(infoLocal.getMedico());
        planilla.setPfL(infoLocal.getPf());
        planilla.setArbitroV(infoVisitante.getArbitro());
        planilla.setDtV(infoVisitante.getDt());
        planilla.setCapitanV(infoVisitante.getCapitan());
        planilla.setGoleadoresV(infoVisitante.getGoleadores());
        planilla.setJuezMesaV(infoVisitante.getJuezMesa());
        planilla.setMedicoV(infoVisitante.getMedico());
        planilla.setPfV(infoVisitante.getPf());

        planilla.setJugadoresLocal(crearColeccionJugadores(planilla, infoLocal));
        planilla.setJugadoresVisitante(crearColeccionJugadores(planilla, infoVisitante));

        DatosEquipoPlanilla datosLocal = planilla.getDatosLocal();
        DatosEquipoPlanilla datosVisitante = planilla.getDatosVisitante();
        updateAmonestaciones(datosLocal, infoLocal.getAmonestaciones());
        updateAmonestaciones(datosVisitante, infoVisitante.getAmonestaciones());
    }

    private void updateAmonestaciones(DatosEquipoPlanilla datos, List<AmonestacionInfo> amonestaciones)
            throws PersistenceException {

        datos.limpiarTarjetas();
        for (AmonestacionInfo ai : amonestaciones) {
            datos.crearTarjetaPartido(jugadorDao.get(ai.getJugadorId()), ai.getRojas(), ai.getAmarillas(), ai
                    .getVerdes());
        }
    }

    @Override
    @Transactional
    public void validarPlanilla(Integer idPartido) throws NoxitException {
        partidoDao.get(idPartido).validarPlanilla();
    }

    @Override
    @Transactional
    public void publicarPlanilla(Integer idPartido) throws NoxitException {
        partidoDao.get(idPartido).publicarPlanilla();
    }

    @Override
    @Transactional
    public void rechazarPlanilla(Integer idPartido, String comentario) throws NoxitException {
        partidoDao.get(idPartido).rechazarPlanilla(comentario);
    }
}
