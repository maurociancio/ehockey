package ar.noxit.ehockey.dao.impl;

import ar.noxit.dataaccessobject.hibernate.HibernateDao;
import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.model.Jugador;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;

public class JugadorDao extends HibernateDao<Jugador, Integer> implements
        IJugadorDao {

    private static final String CONCATENADOR = " AND ";

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
                        "FROM Jugador j WHERE j.club.id = :club_id AND j.ficha IN (:fichasjugadores) AND j.activo = TRUE")
                .setParameter("club_id", clubId).setParameterList(
                        "fichasjugadores", fichasJugadores).list();
    }

    @Override
    public Jugador getActiveJugadorById(Integer jugadorId) {
        return (Jugador) getSession().createQuery(
                "FROM Jugador j WHERE j.ficha = :ficha AND j.activo = TRUE")
                .setParameter("ficha", jugadorId).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Jugador> getAllActive() {
        return getSession().createQuery("FROM Jugador j WHERE j.activo = TRUE")
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Jugador> getJugadorByDNIAndTipoDoc(String dni, String tipoDoc) {
        Validate.notNull(dni);
        Validate.notEmpty(dni);
        Validate.notNull(tipoDoc);
        Validate.notEmpty(tipoDoc);
        return getSession()
                .createQuery(
                        "FROM Jugador j WHERE j.documento.tipo = :tipo AND j.documento.numero = :numero AND j.activo = TRUE")
                .setParameter("tipo", tipoDoc).setParameter("numero", dni)
                .list();

    }

    @Override
    public List<Jugador> getJugadoresFromClubDivisionSector(Integer clubId,
            Integer divisionId, Integer sectorId) {
        // Verifico que al menos un parametro es distinto de null
        if (clubId == null && divisionId == null && sectorId == null) {
            return getAllActive();
        } else {
            return generateQuery(clubId, divisionId, sectorId);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Jugador> generateQuery(Integer clubid, Integer divisionid,
            Integer sectorid) {
        List<Integer> parametros = new ArrayList<Integer>();
        String queryString = "FROM Jugador j WHERE ";
        queryString = evaluar(clubid, true, "j.club.id = :club_id", parametros,
                queryString);
        queryString = evaluar(divisionid, clubid == null,
                "j.division.id = :division_id", parametros, queryString);
        queryString = evaluar(sectorid, clubid == null && divisionid == null,
                "j.sector.id = :sector_id", parametros, queryString);
        queryString += " AND j.activo = TRUE";
        return createQueryString(clubid, divisionid, sectorid, queryString)
                .list();
    }

    private String evaluar(Integer id, boolean esPrimero, String modificador,
            List<Integer> parametros, String query) {
        if (id != null) {
            if (!esPrimero)
                query += CONCATENADOR;
            query += modificador;
            parametros.add(id);
        }
        return query;
    }

    private Query createQueryString(Integer clubid, Integer divisionid,
            Integer sectorid, String queryString) {
        Session session = getSession();
        Query query = session.createQuery(queryString);
        query = (clubid != null) ? query.setParameter("club_id", clubid)
                : query;
        query = (divisionid != null) ? query.setParameter("division_id",
                divisionid) : query;
        query = (sectorid != null) ? query.setParameter("sector_id", sectorid)
                : query;
        return query;
    }
}
