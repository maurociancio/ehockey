package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.model.Jugador;

public class JugadorDao extends HibernateDao<Jugador, Integer> implements IJugadorDao {

    public JugadorDao() {
        super(Jugador.class);
    }
}
