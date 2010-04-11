package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.model.Jugador;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.hibernate.Session;

public class JugadorDao extends HibernateDao<Jugador, Integer> implements IJugadorDao {

    public JugadorDao() {
        super(Jugador.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Jugador> getJugadoresFromClub(Integer clubId, List<Integer> fichasJugadores) {
        Validate.notNull(clubId);
        Validate.notNull(fichasJugadores);

        Session session = getSession();
        if (fichasJugadores.isEmpty()) {
            return new ArrayList<Jugador>();
        }
        return session.createQuery("FROM Jugador j WHERE j.club.id = :club_id AND j.ficha IN (:fichasjugadores)").
                setParameter("club_id", clubId).
                setParameterList("fichasjugadores", fichasJugadores).
                list();
    }
}
