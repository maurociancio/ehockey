package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PartidoYaPerteneceATorneoExcepcion;
import ar.noxit.ehockey.exception.TorneoNoCoincideException;
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

    public void agregarPartido(Partido partido) throws TorneoNoCoincideException, PartidoYaPerteneceATorneoExcepcion {
        Validate.notNull(partido, "el partido no puede ser null");

        if (!this.equals(partido.getTorneo())) {
            throw new TorneoNoCoincideException("el torneo del partido no coincide con this");
        }

        if (partidos.contains(partido)) {
            throw new PartidoYaPerteneceATorneoExcepcion("el partido ya está agregado");
        }

        this.partidos.add(partido);
    }
}
