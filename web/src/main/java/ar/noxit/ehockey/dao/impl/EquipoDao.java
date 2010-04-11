package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IEquipoDao;
import ar.noxit.ehockey.model.Equipo;

public class EquipoDao extends HibernateDao<Equipo, Integer> implements IEquipoDao {

    public EquipoDao() {
        super(Equipo.class);
    }
}
