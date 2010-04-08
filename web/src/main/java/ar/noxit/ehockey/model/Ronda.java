package ar.noxit.ehockey.model;

import java.util.HashSet;
import java.util.Set;

public class Ronda {

    private Set<Partido> partidos = new HashSet<Partido>();

    public Ronda() {
    }

    public void cargarPartidoAlaRonda(Partido partido) {
        partidos.add(partido);
    }

    public boolean existePartidoRonda(Partido partido) {
        return partidos.contains(partido);
    }

    public int getCantidadPartidos() {
        return partidos.size();
    }

}
