package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IPlanillaDao;
import ar.noxit.ehockey.model.PlanillaBase;

public class PlanillaDao extends HibernateDao<PlanillaBase, Integer> implements IPlanillaDao {
    PlanillaDao() {
        super(PlanillaBase.class);
    }
}
