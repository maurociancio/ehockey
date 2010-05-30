package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.web.pages.jugadores.JugadorPlano;
import ar.noxit.exceptions.NoxitException;

public interface IJugadorService {

    public void add(JugadorPlano jugadorPlano) throws NoxitException;

    public void update(JugadorPlano jugadorPlano) throws NoxitException;

    public void remove(Integer integer) throws NoxitException;

    public List<Jugador> getAll() throws NoxitException;

    public List<Jugador> getAllActive() throws NoxitException;

    public List<Jugador> getAllByClubDivisionSector(Integer clubid,
            Integer divisionid, Integer sectorid) throws NoxitException;

    public Jugador get(Integer id) throws NoxitException;

    public Jugador getActive(Integer id) throws NoxitException;

    public JugadorPlano aplanar(Jugador jugador);
}
