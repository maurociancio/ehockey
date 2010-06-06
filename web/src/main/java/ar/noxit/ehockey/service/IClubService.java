package ar.noxit.ehockey.service;

import java.util.List;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.exception.ClubYaExistenteException;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.web.pages.clubes.ClubPlano;
import ar.noxit.exceptions.NoxitException;

public interface IClubService {
    /**
     * Devuelve todos los jugadores del club, que pueden jugar en el equipo
     * 
     * @param equipoId
     *            id del equipo para el cual se obtienen los jugadores
     * @return
     */
    List<Jugador> getJugadoresParaEquipo(Integer equipoId) throws NoxitException;

    public Club get(Integer id) throws NoxitException;

    List<Club> getAll() throws NoxitException;

    List<Jugador> getJugadoresPorClub(Integer clubId, List<Integer> idJugadores) throws NoxitException;

    List<Equipo> getEquiposPorClub(Integer clubId) throws NoxitException;

    public List<ClubPlano> getAllPlano() throws NoxitException;

    public void save(ClubPlano clubPlano) throws NoxitException;

    public void update(ClubPlano clubPlano) throws NoxitException;

    public IModel<ClubPlano> aplanar(IModel<Club> model);

    public void verificarNombreClub(ClubPlano clubPlano) throws ClubYaExistenteException;

    public void verificarCambioNombre(ClubPlano clubPlano) throws ClubYaExistenteException;
}
