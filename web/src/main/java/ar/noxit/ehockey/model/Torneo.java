package ar.noxit.ehockey.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.Validate;

public class Torneo {

    private String nombre;
    private Set<Partido> partidos;

    public Torneo(String nombre) {
        Validate.notNull(nombre, "nombre no puede ser null");

        this.nombre = nombre;
        this.partidos = new HashSet<Partido>();
    }

    public String getNombre() {
        return nombre;
    }

    public Iterator<Partido> iteradorPartidos() {
        return partidos.iterator();
    }
}
