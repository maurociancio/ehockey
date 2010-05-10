package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.Validate;

import ar.noxit.ehockey.exception.NoHayPartidoSiguienteException;
import ar.noxit.ehockey.exception.PartidoYaPerteneceATorneoExcepcion;
import ar.noxit.ehockey.exception.TorneoNoCoincideException;
import ar.noxit.exceptions.NoxitRuntimeException;

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

    public Partido getProximoPartidoDe(Partido partido, Equipo equipo) throws NoHayPartidoSiguienteException {
        Validate.notNull(partido);
        Validate.notNull(equipo);

        List<Partido> partidos = getPartidosDe(equipo);
        Collections.sort(partidos, new Comparator<Partido>() {

            @Override
            public int compare(Partido o1, Partido o2) {
                Integer rueda = o1.getRueda() - o2.getRueda();
                if (rueda != 0) {
                    return rueda;
                } else {
                    Integer fecha = o1.getFechaDelTorneo() - o2.getFechaDelTorneo();
                    if (fecha != 0)
                        return fecha;
                }
                throw new NoxitRuntimeException("Hay un partido repetido en el torneo");
            }
        });

        try {
            return partidos.get(partidos.indexOf(partido) + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoHayPartidoSiguienteException(e);
        }
    }

    private List<Partido> getPartidosDe(Equipo equipo) {
        List<Partido> p = new ArrayList<Partido>();
        for (Partido partido : partidos) {
            if (partido.juega(equipo)) {
                p.add(partido);
            }
        }
        return p;
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
