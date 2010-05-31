package ar.noxit.ehockey.dao;

import ar.noxit.dataaccessobject.IDao;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.exceptions.persistence.PersistenceException;
import java.util.List;

public interface IEquipoDao extends IDao<Equipo, Integer> {

    List<Equipo> getEquiposDe(Integer sector, Integer division) throws PersistenceException;

    List<Equipo> getAllActivo() throws PersistenceException;
}
