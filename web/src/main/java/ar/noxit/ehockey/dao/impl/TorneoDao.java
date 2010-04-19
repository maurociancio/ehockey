package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.ITorneoDao;
import ar.noxit.ehockey.model.Torneo;

public class TorneoDao extends HibernateDao<Torneo, Integer> implements ITorneoDao {

    public TorneoDao() {
        super(Torneo.class);
    }
}
