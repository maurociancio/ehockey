package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface IClubService {

    List<Jugador> getJugadoresPorClub(Integer clubId) throws NoxitException;

    Club getClub(Integer id) throws NoxitException;

    List<Club> getAll() throws NoxitException;

    List<Jugador> getJugadoresPorClub(Integer clubId, List<Integer> idJugadores) throws NoxitException;

    List<Equipo> getEquiposPorClub(Integer clubId) throws NoxitException;
}
