package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.web.pages.jugadores.JugadorPlano;
import ar.noxit.exceptions.NoxitException;

public interface IJugadorService {

    public void add(Jugador jugador) throws NoxitException;

    public void update(Jugador jugador) throws NoxitException;

    public void remove(Jugador jugador) throws NoxitException;

    public List<Jugador> getAll() throws NoxitException;

    public Jugador get(Integer id) throws NoxitException;

    public void convertAdd(JugadorPlano jugador) throws NoxitException;

    public List<JugadorPlano> getAllPlano() throws NoxitException;
}
