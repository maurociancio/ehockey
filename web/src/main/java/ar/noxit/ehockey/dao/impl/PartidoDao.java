package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IPartidoDao;
import ar.noxit.ehockey.model.Partido;

public class PartidoDao extends HibernateDao<Partido, Integer> implements
        IPartidoDao {

    public PartidoDao() {
        super(Partido.class);
    }
}
