package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IPlanillaDao;
import ar.noxit.ehockey.model.Planilla;

public class PlanillaDao extends HibernateDao<Planilla, Integer> implements IPlanillaDao {
    PlanillaDao() {
        super(Planilla.class);
    }
}
