package ar.noxit.ehockey.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TablaPosiciones {

    private Torneo torneo;
    private Map<Equipo, DatosTabla> tabla = new HashMap<Equipo, DatosTabla>();
    private Iterator<Partido> partidos;
    private Set<Partido> partidosFiltrados;

    public TablaPosiciones(Torneo torneo) {
        this.torneo = torneo;
        partidos = torneo.iteradorPartidos();
        partidosFiltrados = new HashSet<Partido>();
    }

    public TablaPosiciones filtroTabla(Division division) {
        partidos = getPartidosByDivision(division);
        return this;
    }

    public TablaPosiciones filtroTabla(Sector sector) {
        partidos = getPartidosBySector(sector);
        return this;
    }

    public DatosTabla getDatosTabla(Equipo equipo) {
        if (!tabla.containsKey(equipo))
            return new DatosTabla();
        return tabla.get(equipo);
    }

    public TablaPosiciones calcularTabla() {
        // Limpio la tabla de datos
        tabla.clear();
        // Calculo los datos de cada equipo
        while (partidos.hasNext()) {
            Partido partido = partidos.next();
            if (partido.isJugado()) {
                resolverPartido(partido.getLocal(), partido.getGolesLocal()
                        .intValue(), partido.getGolesVisitante().intValue());
                resolverPartido(partido.getVisitante(), partido
                        .getGolesVisitante().intValue(), partido
                        .getGolesLocal().intValue());
            }
        }
        return this;
    }

    private void resolverPartido(Equipo equipo, int golesFavor, int golesContra) {
        if (tabla.containsKey(equipo)) {
            tabla.get(equipo).jugarPartido(golesFavor, golesContra);
        } else {
            tabla.put(equipo, new DatosTabla().jugarPartido(golesFavor,
                    golesContra));
        }
    }

    private Iterator<Partido> getPartidosByDivision(Division division) {
        Set<Partido> set = new HashSet<Partido>();
        Iterator<Partido> it = (partidosFiltrados.isEmpty()) ? this.torneo
                .iteradorPartidos() : this.partidosFiltrados.iterator();
        while (it.hasNext()) {
            Partido partido = it.next();
            if (partido.getLocal().getDivision().equals(division)
                    && partido.getVisitante().getDivision().equals(division)) {
                set.add(partido);
            }
        }
        return set.iterator();
    }

    private Iterator<Partido> getPartidosBySector(Sector sector) {
        Set<Partido> set = new HashSet<Partido>();
        Iterator<Partido> it = (partidosFiltrados.isEmpty()) ? this.torneo
                .iteradorPartidos() : this.partidosFiltrados.iterator();
        while (it.hasNext()) {
            Partido partido = it.next();
            if (partido.getLocal().getSector().equals(sector)
                    && partido.getVisitante().getSector().equals(sector)) {
                set.add(partido);
            }
        }
        return set.iterator();
    }
}
