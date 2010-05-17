package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IPlanillaDao;
import ar.noxit.ehockey.model.PlanillaFinal;

public class PlanillaDao extends HibernateDao<PlanillaFinal, Integer> implements IPlanillaDao {
    PlanillaDao() {
        super(PlanillaFinal.class);
    }
}
