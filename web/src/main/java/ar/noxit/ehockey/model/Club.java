package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.EquipoInexistenteException;
import ar.noxit.ehockey.exception.SinClubException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.Validate;

public class Club {

    private Integer id;

    private String nombre;
    private String nombreCompleto;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private String provincia;
    private String email;
    private String telefono;
    private String web;
    private String observaciones;

    private boolean activo;

    private Set<Equipo> equipos = new HashSet<Equipo>();
    private Set<Jugador> jugadores = new HashSet<Jugador>();

    public Club(String nombreCompleto) {
        Validate.notNull(nombreCompleto, "nombre completo no puede ser null");

        this.nombreCompleto = nombreCompleto;
    }

    public Equipo crearNuevoEquipo(String nombreEquipo, Division division, Sector sector) {
        Equipo equipo = new Equipo(nombreEquipo, this, division, sector);
        equipos.add(equipo);
        return equipo;
    }

    public void borrarEquipo(Equipo equipo) throws EquipoInexistenteException {
        Validate.notNull(equipo);

        if (!equipos.contains(equipo)) {
            throw new EquipoInexistenteException("equipo no existe en este club");
        }

        equipos.remove(equipo);
        equipo.eliminar();
    }

    public void borrarJugador(Jugador jugador) {
        Validate.notNull(jugador);

        if (jugadores.contains(jugador)) {
            jugadores.remove(jugador);
        }
        jugador.liberar();
    }

    public Iterator<Equipo> iteratorEquipos() {
        return equipos.iterator();
    }

    public void agregarJugador(Jugador jugador) {
        Validate.notNull(jugador);

        if (!jugadores.contains(jugador)) {
            this.jugadores.add(jugador);
        }

        try {
            if (!this.equals(jugador.getClub())) {
                jugador.asignarClub(this);
            }
        } catch (SinClubException e) {
            jugador.asignarClub(this);
        }
    }

    public boolean contiene(Jugador jugador) {
        Validate.notNull(jugador);
        return jugadores.contains(jugador);
    }

    public String getNombre() {
        return nombreCompleto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Club other = (Club) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public Set<Equipo> getEquipos() {
        return equipos;
    }

    protected Club() {
    }
}
