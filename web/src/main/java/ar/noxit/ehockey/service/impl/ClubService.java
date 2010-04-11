package ar.noxit.ehockey.service.impl;

import ar.noxit.exceptions.persistence.PersistenceException;
import ar.noxit.ehockey.dao.IClubDao;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class ClubService implements IClubService {

    private IClubDao clubDao;

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
    public List<Jugador> getJugadoresPorClub(Integer clubId, List<Integer> idJugadores) throws NoxitException {
        Club club = clubDao.get(clubId);

        List<Jugador> list = new ArrayList<Jugador>(club.getJugadores());
        Iterator<Jugador> it = list.iterator();
        while (it.hasNext()) {
            Jugador next = it.next();
            if (!idJugadores.contains(next.getFicha())) {
                it.remove();
            }
        }
        return list;
    }

    @Override
    public List<Equipo> getEquiposPorClub(Integer clubId) throws NoxitException {
        Club club = clubDao.get(clubId);
        return new ArrayList<Equipo>(club.getEquipos());
    }

    public void setClubDao(IClubDao clubDao) {
        this.clubDao = clubDao;
    }
}
