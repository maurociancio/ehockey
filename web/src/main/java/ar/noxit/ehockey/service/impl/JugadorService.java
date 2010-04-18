package ar.noxit.ehockey.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;

public class JugadorService implements IJugadorService {

    private IJugadorDao jugadorDao;

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void add(Jugador jugador) throws NoxitException {
        if (jugador == null) {
            throw new IllegalArgumentException();
        }
        jugadorDao.save(jugador);
    }

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void remove(Jugador jugador) throws NoxitException {
        if (jugador == null) {
            throw new IllegalArgumentException();
        }
        jugadorDao.delete(jugador);
    }

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void update(Jugador jugador) throws NoxitException {
        if (jugador == null) {
            throw new IllegalArgumentException();
        }
        jugadorDao.get(jugador.getFicha());
    }

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public Jugador get(Integer id) throws NoxitException {
        return jugadorDao.get(id);
    }

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public List<Jugador> getAll() throws NoxitException {
        return jugadorDao.getAll();
    }

    public void setJugadorDao(IJugadorDao jugadorDao) {
        this.jugadorDao = jugadorDao;
    }
}
