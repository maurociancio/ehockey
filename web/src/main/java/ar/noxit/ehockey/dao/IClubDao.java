package ar.noxit.ehockey.dao;

import ar.noxit.dataaccessobject.IDao;
import ar.noxit.ehockey.model.Club;
import java.util.List;

public interface IClubDao extends IDao<Club, Integer> {

    public List<Club> getClubPorNombre(String nombre, String nombreCompleto);
}
