package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.IClubDao;
import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class ClubService implements IClubService {

    private IClubDao clubDao;
    private IJugadorDao jugadorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Jugador> getJugadoresPorClub(Integer clubId) throws NoxitException {
        Club club = clubDao.get(clubId);
        return new ArrayList<Jugador>(club.getJugadores());
    }

    @Override
    @Transactional(readOnly = true)
    public Club getClub(Integer id) throws NoxitException {
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

    public void setClubDao(IClubDao clubDao) {
        this.clubDao = clubDao;
    }

    public void setJugadorDao(IJugadorDao jugadorDao) {
        this.jugadorDao = jugadorDao;
    }
}
