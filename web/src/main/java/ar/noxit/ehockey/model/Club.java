package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.EquipoInexistenteException;
import ar.noxit.ehockey.exception.SinClubException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDate;

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

    public Jugador crearNuevoJugador(String apellido, String nombre, Sector sector, Division division, LocalDate now) {
        Jugador jugador = new Jugador(apellido, nombre, this, sector, division, now);
        agregarJugador(jugador);
        return jugador;
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

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public Set<Equipo> getEquipos() {
        return equipos;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipos(Set<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public String toString() {
        return nombre;
    }

    protected Club() {
    }
}
