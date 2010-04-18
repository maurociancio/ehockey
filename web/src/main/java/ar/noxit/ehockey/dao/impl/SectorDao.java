package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.ISectorDao;
import ar.noxit.ehockey.model.Sector;

public class SectorDao extends HibernateDao<Sector, Integer> implements
        ISectorDao {
    public SectorDao() {
        super(Sector.class);
    }

}
