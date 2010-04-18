package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.exceptions.NoxitException;

public interface IJugadorService {
    
    public void add(Jugador jugador) throws NoxitException;
    
    public void update(Jugador jugador) throws NoxitException;
    
    public void remove(Jugador jugador) throws NoxitException;

    public List<Jugador> getAll() throws NoxitException;

    Jugador get(Integer id) throws NoxitException;
}
