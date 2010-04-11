package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IClubDao;
import ar.noxit.ehockey.model.Club;

public class ClubDao extends HibernateDao<Club, Integer> implements IClubDao {

    public ClubDao() {
        super(Club.class);
    }
}
