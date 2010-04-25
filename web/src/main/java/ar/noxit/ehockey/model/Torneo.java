package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.Validate;

import ar.noxit.ehockey.exception.PartidoYaPerteneceATorneoExcepcion;
import ar.noxit.ehockey.exception.TorneoNoCoincideException;

public class Torneo {

    private Integer id;
    private String nombre;
    private Set<Partido> partidos;

    public Torneo(String nombre) {
        Validate.notNull(nombre, "nombre no puede ser null");

        this.nombre = nombre;
        this.partidos = new HashSet<Partido>();
    }

    public int getCantidadPartidos() {
        return partidos.size();
    }

    public String getNombre() {
        return nombre;
    }

    public Iterator<Partido> iteradorPartidos() {
        return partidos.iterator();
    }

    public void agregarPartido(Partido partido)
            throws TorneoNoCoincideException,
            PartidoYaPerteneceATorneoExcepcion {
        Validate.notNull(partido, "el partido no puede ser null");

        if (!this.equals(partido.getTorneo())) {
            throw new TorneoNoCoincideException(
                    "el torneo del partido no coincide con this");
        }

        if (partidos.contains(partido)) {
            throw new PartidoYaPerteneceATorneoExcepcion(
                    "el partido ya está agregado");
        }

        this.partidos.add(partido);
    }

    public TablaPosiciones crearTablaPosiciones() {
        return new TablaPosiciones(this);
    }

    public Integer getId() {
        return id;
    }

    public Torneo() {
    }
}
