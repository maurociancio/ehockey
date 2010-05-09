package ar.noxit.ehockey.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.model.Jugador;

public class JugadorDao extends HibernateDao<Jugador, Integer> implements
        IJugadorDao {

    public JugadorDao() {
        super(Jugador.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Jugador> getJugadoresFromClub(Integer clubId,
            List<Integer> fichasJugadores) {
        Validate.notNull(clubId);
        Validate.notNull(fichasJugadores);

        Session session = getSession();
        if (fichasJugadores.isEmpty()) {
            return new ArrayList<Jugador>();
        }
        return session
                .createQuery(
                        "FROM Jugador j WHERE j.club.id = :club_id AND j.ficha IN (:fichasjugadores)")
                .setParameter("club_id", clubId).setParameterList(
                        "fichasjugadores", fichasJugadores).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Jugador> getJugadoresFromClubDivisionSector(Integer clubId,
            Integer divisionId, Integer sectorId) {
        // Verifico que al menos un parametro es distinto de null
        Validate.isTrue(clubId != null || divisionId != null
                || sectorId != null);
        return generateQuery(clubId, divisionId, sectorId);
    }

    private List generateQuery(Integer clubid, Integer divisionid,
            Integer sectorid) {
        Session session = getSession();
        List<Integer> parametros = new ArrayList<Integer>();
        String queryString = "FROM Jugador j WHERE ";
        if (clubid != null) {
            queryString += "j.club.id = :club_id";
            parametros.add(clubid);
        }
        if (divisionid != null) {
            if (clubid != null)
                queryString += " AND ";
            queryString += "j.division.id = :division_id";
            parametros.add(divisionid);
        }
        if (sectorid != null) {
            if (clubid != null || divisionid != null)
                queryString += " AND ";
            queryString += "j.sector.id = :sector_id";
            parametros.add(sectorid);
        }
        Query query = session.createQuery(queryString);
        query = (clubid != null) ? query.setParameter("club_id", clubid)
                : query;
        query = (divisionid != null) ? query.setParameter("division_id",
                divisionid) : query;
        query = (sectorid != null) ? query.setParameter("sector_id", sectorid)
                : query;
        return query.list();
    }
}
