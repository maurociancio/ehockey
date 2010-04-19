package ar.noxit.ehockey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.exception.SinClubException;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.jugadores.ClubPlano;
import ar.noxit.ehockey.web.pages.jugadores.DivisionPlano;
import ar.noxit.ehockey.web.pages.jugadores.JugadorPlano;
import ar.noxit.ehockey.web.pages.jugadores.SectorPlano;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

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

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void convertAdd(JugadorPlano jugador) throws NoxitException {
        Jugador jdr = assembly(jugador);
        jugadorDao.save(jdr);

    }

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public List<JugadorPlano> getAllPlano() throws NoxitException {
        List<JugadorPlano> jugadores = new ArrayList<JugadorPlano>();
        for (Jugador each : jugadorDao.getAll()) {
            jugadores.add(aplanar(each));
        }
        return jugadores;
    }

    private Jugador assembly(JugadorPlano jugador) {
        Jugador jdr = new Jugador(jugador.getApellido(), jugador.getNombre(),
                new Club(jugador.getClub().getNombre()), new Sector(jugador
                        .getSector().getSector()), new Division(jugador
                        .getDivision().getDivision()));
        jdr.setFechaAlta(jugador.getFechaAlta());
        jdr.setFechaNacimiento(jugador.getFechaNacimiento());
        jdr.setLetraJugador(jugador.getLetraJugador());
        jdr.setNumeroDocumento(jugador.getNumeroDocumento());
        jdr.setTipoDocumento(jugador.getTipoDocumento());
        jdr.setTelefono(jugador.getTelefono());
        return jdr;
    }

    private JugadorPlano aplanar(Jugador jugador) {
        JugadorPlano jdr = new JugadorPlano();
        jdr.setApellido(jugador.getApellido());
        try {
            jdr.setClub(jugador.getClub());
        } catch (SinClubException e) {
            throw new NoxitRuntimeException(e);
        }
        jdr.setDivision(jugador.getDivision());
        jdr.setFechaAlta(jugador.getFechaAlta());
        jdr.setFechaNacimiento(jugador.getFechaNacimiento());
        jdr.setFicha(jugador.getFicha());
        jdr.setLetraJugador(jugador.getLetraJugador());
        jdr.setNombre(jugador.getNombre());
        jdr.setNumeroDocumento(jugador.getDocumento());
        jdr.setSector(jugador.getSector());
        jdr.setTelefono(jugador.getTelefono());
        jdr.setTipoDocumento(jugador.getTipoDocumento());
        return jdr;
    }

    public void setJugadorDao(IJugadorDao jugadorDao) {
        this.jugadorDao = jugadorDao;
    }
}
