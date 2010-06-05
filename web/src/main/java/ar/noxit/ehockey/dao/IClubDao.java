package ar.noxit.ehockey.dao;

import java.util.List;

import ar.noxit.dataaccessobject.IDao;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Jugador;

public interface IClubDao extends IDao<Club, Integer> {

    public List<Jugador> getClubPorNombre(String nombre, String nombreCompleto);
}
