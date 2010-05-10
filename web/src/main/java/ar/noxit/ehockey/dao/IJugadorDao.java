package ar.noxit.ehockey.dao;

import ar.noxit.dataaccessobject.IDao;
import ar.noxit.ehockey.model.Jugador;
import java.util.List;

public interface IJugadorDao extends IDao<Jugador, Integer> {

    List<Jugador> getJugadoresFromClub(Integer clubId,
            List<Integer> fichasJugadores);

    public List<Jugador> getJugadoresFromClubDivisionSector(Integer clubId,
            Integer divisionId, Integer sectorId);

    public List<Jugador> getJugadorByDNIAndTipoDoc(String dni, String tipoDoc);

    public List<Jugador> getAllActive();

    public Jugador getActiveJugadorById(Integer jugadorId);

}
