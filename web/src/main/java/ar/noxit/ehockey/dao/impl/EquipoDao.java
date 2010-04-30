package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IEquipoDao;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.exceptions.persistence.PersistenceException;
import java.util.List;
import org.hibernate.Session;

public class EquipoDao extends HibernateDao<Equipo, Integer> implements IEquipoDao {

    public EquipoDao() {
        super(Equipo.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Equipo> getEquiposDe(Integer sector, Integer division) throws PersistenceException {
        Session session = getSession();
        return session.createQuery("from Equipo e where e.division.id = :division and e.sector.id = :sector")
                .setParameter("division", division)
                .setParameter("sector", sector)
                .list();
    }
}
