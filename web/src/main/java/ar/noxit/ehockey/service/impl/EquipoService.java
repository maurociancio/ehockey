package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.IClubDao;
import ar.noxit.ehockey.dao.IDivisionDao;
import ar.noxit.ehockey.dao.IEquipoDao;
import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.dao.ISectorDao;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.transfer.EquipoPlano;
import ar.noxit.exceptions.NoxitException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Transactional;

public class EquipoService implements IEquipoService {

    private IEquipoDao equipoDao;
    private IJugadorDao jugadorDao;
    private IClubDao clubDao;
    private ISectorDao sectorDao;
    private IDivisionDao divisionDao;

    @Override
    @Transactional(readOnly = true)
    public Equipo get(Integer id) throws NoxitException {
        return equipoDao.get(id);
    }

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void asignarListaBuenaFe(Integer equipoId, List<Integer> jugadoresIds) throws NoxitException {
        Equipo equipo = equipoDao.get(equipoId);

        List<Jugador> jugadores = new ArrayList<Jugador>();
        for (Integer jugadorId : jugadoresIds) {
            jugadores.add(jugadorDao.get(jugadorId));
        }

        equipo.getListaBuenaFe().reemplazarJugadores(jugadores);
    }

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void add(EquipoPlano equipoPlano) throws NoxitException {
        Validate.notNull(equipoPlano);

        Club club = clubDao.get(equipoPlano.getClubId());
        Division division = divisionDao.get(equipoPlano.getDivisionId());
        Sector sector = sectorDao.get(equipoPlano.getSectorId());

        club.crearNuevoEquipo(equipoPlano.getNombre(), division, sector);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipo> getEquiposDe(Integer sector, Integer division) throws NoxitException {
        return equipoDao.getEquiposDe(sector, division);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipo> getAll() throws NoxitException {
        return equipoDao.getAll();
    }

    public void setEquipoDao(IEquipoDao equipoDao) {
        this.equipoDao = equipoDao;
    }

    public void setJugadorDao(IJugadorDao jugadorDao) {
        this.jugadorDao = jugadorDao;
    }

    public void setClubDao(IClubDao clubDao) {
        this.clubDao = clubDao;
    }

    public void setDivisionDao(IDivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }

    public void setSectorDao(ISectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }
}
