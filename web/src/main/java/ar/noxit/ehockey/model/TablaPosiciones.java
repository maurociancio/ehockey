package ar.noxit.ehockey.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TablaPosiciones {

    private Torneo torneo;
    private Map<Equipo, DatosTabla> tabla = new HashMap<Equipo, DatosTabla>();
    private Iterator<Partido> partidos;

    public TablaPosiciones(Torneo torneo) {
        this.torneo = torneo;
        partidos = torneo.iteradorPartidos();
    }

    public TablaPosiciones filtroTabla(Division division) {
        partidos = torneo.getPartidosByDivision(division);
        return this;
    }

    public TablaPosiciones filtroTabla(Sector sector) {
        partidos = torneo.getPartidosBySector(sector);
        return this;
    }

    public DatosTabla getDatosTabla(Equipo equipo) {
        return tabla.get(equipo);
    }

    public TablaPosiciones calcularTabla() {
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
}
