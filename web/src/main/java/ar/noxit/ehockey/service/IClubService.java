package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Jugador;
import java.util.List;

public interface IClubService {

    List<Jugador> getJugadoresPorClub(Integer clubId);

    Club getClub(Integer id);

    List<Club> getAll();

    List<Jugador> getJugadoresPorClub(Integer clubId, List<Integer> seleccionados);
}
