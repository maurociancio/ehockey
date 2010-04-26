package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;

import ar.noxit.ehockey.exception.EquiposInvalidosException;
import ar.noxit.ehockey.exception.FechaInvalidaException;
import ar.noxit.ehockey.exception.PartidoNoJugadoPorEquipoException;
import ar.noxit.ehockey.exception.PartidoNoTerminadoException;
import ar.noxit.ehockey.exception.PartidoYaPerteneceATorneoExcepcion;
import ar.noxit.ehockey.exception.TorneoNoCoincideException;
import ar.noxit.ehockey.exception.ViolacionReglaNegocioException;

public class Equipo {

    private Integer id;
    private String nombre;

    private Division division;
    private Sector sector;
    private Club club;

    private ListaBuenaFe listaBuenaFe;

    private Integer puntaje;
    private Integer jugados;

    /**
     * No debe ser usado por los clientes
     * 
     * @param nombre
     * @param club
     */
    public Equipo(String nombre, Club club, Division division, Sector sector) {
        Validate.notNull(nombre, "nombre no puede ser null");
        Validate.notNull(club, "club no puede ser null");
        Validate.notNull(division, "division no puede ser null");
        Validate.notNull(sector, "sector no puede ser null");

        this.club = club;
        this.nombre = nombre;
        this.division = division;
        this.sector = sector;
        this.puntaje = new Integer(0);
        this.jugados = new Integer(0);
    }

    public ListaBuenaFe getListaBuenaFe() {
        if (listaBuenaFe == null) {
            listaBuenaFe = new ListaBuenaFe(this);
        }
        return listaBuenaFe;
    }

    public Partido jugarContra(Torneo torneo, Equipo visitante,
            Integer fechaDelTorneo, LocalDateTime inicio, LocalDateTime now)
            throws EquiposInvalidosException, FechaInvalidaException {

        try {
            Partido partido = new Partido(torneo, this, visitante,
                    fechaDelTorneo, inicio, now);
            torneo.agregarPartido(partido);
            return partido;
        } catch (TorneoNoCoincideException e) {
            // no deberia pasar nunca ya que el torneo del partido coincide
            throw new ViolacionReglaNegocioException(e);
        } catch (PartidoYaPerteneceATorneoExcepcion e) {
            // no deberia pasar nunca por que el partido esta recien creado
            throw new ViolacionReglaNegocioException(e);
        }
    }

    public void ganarPartido(Partido partido)
            throws PartidoNoTerminadoException,
            PartidoNoJugadoPorEquipoException {
        if (!partido.isJugado())
            throw new PartidoNoTerminadoException();
        if (partido.getLocal().equals(this)
                && partido.getVisitante().equals(this))
            throw new PartidoNoJugadoPorEquipoException();
        jugados = new Integer(jugados + 1);
        puntaje = new Integer(puntaje + 3);
    }

    public void perderPartido(Partido partido)
            throws PartidoNoTerminadoException,
            PartidoNoJugadoPorEquipoException {
        if (!partido.isJugado())
            throw new PartidoNoTerminadoException();
        if (partido.getLocal().equals(this)
                && partido.getVisitante().equals(this))
            throw new PartidoNoJugadoPorEquipoException();
        jugados = new Integer(jugados + 1);
    }

    public void empatarPartido(Partido partido)
            throws PartidoNoTerminadoException,
            PartidoNoJugadoPorEquipoException {
        if (!partido.isJugado())
            throw new PartidoNoTerminadoException();
        if (partido.getLocal().equals(this)
                && partido.getVisitante().equals(this))
            throw new PartidoNoJugadoPorEquipoException();
        jugados = new Integer(jugados + 1);
        puntaje = new Integer(puntaje + 1);
    }

    public Integer getPuntaje() {
        return this.puntaje;
    }

    public Division getDivision() {
        return division;
    }

    public Sector getSector() {
        return sector;
    }

    /**
     * No debe ser llamados por los clientes
     */
    public void eliminar() {
        this.club = null;
    }

    public String getNombre() {
        return nombre;
    }

    public Club getClub() {
        return club;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected Equipo() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        Equipo other = (Equipo) obj;
        boolean res = false;
        if (this.nombre.equals(other.getNombre())) {
            res = true;
        }
        return res;
    }
}
