package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.web.pages.jugadores.ClubPlano;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface IClubService {

    List<Jugador> getJugadoresPorClub(Integer clubId) throws NoxitException;

    public Club get(Integer id) throws NoxitException;

    List<Club> getAll() throws NoxitException;

    List<Jugador> getJugadoresPorClub(Integer clubId, List<Integer> idJugadores)
            throws NoxitException;

    List<Equipo> getEquiposPorClub(Integer clubId) throws NoxitException;

    public List<ClubPlano> getAllPlano() throws NoxitException;

}
