package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Division {

    private List<Ronda> rondas = new ArrayList<Ronda>();
    private Set<Equipo> equipos = new HashSet<Equipo>();
    private String division;

    public Division(String division) {
        this.division = division;
    }

    public int getCantidadRondas() {
        return this.rondas.size();
    }

    public int getCantidadEquipos() {
        return this.equipos.size();
    }

    public void agregarRonda(Ronda ronda) {
        this.rondas.add(ronda);
    }

    public boolean existeRonda(Ronda ronda) {
        return rondas.contains(ronda);
    }

    public Ronda obtenerRonda(int indice) {
        return rondas.get(indice);
    }

    public void agregarEquipo(Equipo equipo) {
        this.equipos.add(equipo);
    }

    public boolean existeEquipo(Equipo equipo) {
        return equipos.contains(equipo);
    }

    public Equipo obtenerEquipo(String nombre) {
        Equipo equipo = null;
        for (Equipo each : equipos) {
            if (each.getNombre() == nombre) {
                equipo = each;
                break;
            }
        }
        return equipo;
    }

    public String getNombre() {
        return this.division;
    }

    // Ver si hace falta, o si los partidos se cargan despues de generado el
    // fixture.
    // De esta manera genero tres rondas completas con los equipos exitentes.
    public void generarDivision() {
        int cantidadRondas = this.rondas.size();
        // se vienen muchos for's :P
        for (int i = 0; i < cantidadRondas; ++i) {
            Ronda ronda = rondas.get(i);
            Iterator<Equipo> locales = equipos.iterator();
            while (locales.hasNext()) {
                Iterator<Equipo> visitantes = equipos.iterator();
                Equipo local = locales.next();
                while (visitantes.hasNext()) {
                    Equipo visitante = visitantes.next();
                    if (local.equals(visitante))
                        continue;
                    Partido partido = new Partido(local, visitante);
                    if (!ronda.existePartidoRonda(partido)) {
                        ronda.cargarPartidoAlaRonda(partido);
                    }
                }
            }
        }
    }
}
