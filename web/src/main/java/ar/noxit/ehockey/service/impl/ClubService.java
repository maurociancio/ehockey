package ar.noxit.ehockey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IClubDao;
import ar.noxit.ehockey.dao.IEquipoDao;
import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.clubes.ClubPlano;
import ar.noxit.exceptions.NoxitException;

public class ClubService implements IClubService {

    private IClubDao clubDao;
    private IJugadorDao jugadorDao;
    private IEquipoDao equipoDao;

    @Transactional(readOnly = true)
    private List<Jugador> getJugadoresPorClub(Integer clubId) throws NoxitException {
        Club club = clubDao.get(clubId);
        return new ArrayList<Jugador>(club.getJugadores());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jugador> getJugadoresParaEquipo(Integer equipoId) throws NoxitException {
        Equipo equipo = equipoDao.get(equipoId);
        List<Jugador> jugadores = getJugadoresPorClub(equipo.getClub().getId());
        List<Jugador> nueva = new ArrayList<Jugador>();
        for (Jugador jug : jugadores) {
            if (jug.getDivision().equals(equipo.getDivision()) && jug.getSector().equals(equipo.getSector())) {
                nueva.add(jug);
            }
        }
        return nueva;
    }

    @Override
    @Transactional(readOnly = true)
    public Club get(Integer id) throws NoxitException {
        return clubDao.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Club> getAll() throws NoxitException {
        return clubDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jugador> getJugadoresPorClub(Integer clubId, List<Integer> idJugadores) throws NoxitException {
        return jugadorDao.getJugadoresFromClub(clubId, idJugadores);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipo> getEquiposPorClub(Integer clubId) throws NoxitException {
        Club club = clubDao.get(clubId);
        return new ArrayList<Equipo>(club.getEquipos());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClubPlano> getAllPlano() throws NoxitException {
        List<ClubPlano> clubes = new ArrayList<ClubPlano>();
        for (Club each : clubDao.getAll()) {
            clubes.add(aplanar(each));
        }
        return clubes;
    }

    private ClubPlano aplanar(Club club) {
        ClubPlano clb = new ClubPlano();
        clb.setId(club.getId());
        clb.setNombre(club.getNombre());
        return clb;
    }

    @Override
    @Transactional
    public void save(ClubPlano clubPlano) throws NoxitException {
        Club club = new Club(clubPlano.getNombreCompleto());
        BeanUtils.copyProperties(clubPlano, club);
        clubDao.save(club);
    }

    @Override
    @Transactional
    public void update(ClubPlano clubPlano) throws NoxitException {
        // Modifico los valores del club.
    }

    public void setClubDao(IClubDao clubDao) {
        this.clubDao = clubDao;
    }

    public void setJugadorDao(IJugadorDao jugadorDao) {
        this.jugadorDao = jugadorDao;
    }

    public void setEquipoDao(IEquipoDao equipoDao) {
        this.equipoDao = equipoDao;
    }
}
