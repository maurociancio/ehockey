package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IDivisionDao;
import ar.noxit.ehockey.model.Division;

public class DivisionDao extends HibernateDao<Division, Integer> implements
        IDivisionDao {

    public DivisionDao() {
        super(Division.class);
    }
}