package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;

public interface IClubService {

    List<Jugador> getJugadoresPorClub(Integer clubId);

    Club getClub(Integer id);

    List<Club> getAll();

    List<Jugador> getJugadoresPorClub(Integer clubId, List<Integer> idJugadores);
    
    List<Equipo> getEquiposPorClub(Integer clubId);
}
