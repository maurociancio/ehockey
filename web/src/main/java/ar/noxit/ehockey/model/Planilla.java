package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Planilla {
    boolean precargada = false;

    Equipo local;
    Equipo visitante;

    String dTL;
    String capitanL;
    String pFisicoL;
    String medicoL;
    String juezDeMesaL;
    String arbitroL;

    String dTV;
    String capitanV;
    String pFisicoV;
    String medicoV;
    String juezDeMesaV;
    String arbitroV;

    String observaciones;

    List<Jugador> jugadoresL = new ArrayList<Jugador>();
    List<Jugador> jugadoresV = new ArrayList<Jugador>();

    // TODO definir el atributo fecha
    // TODO definir el resto de los atributos que son: Torneo, Rueda, Partido,
    // Sector(damas/caballeros), categoría, división, zona

    List<Jugador> goleadoresLocal = new ArrayList<Jugador>();
    List<Jugador> goleadoresVisitante = new ArrayList<Jugador>();

    public Planilla(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    /**
     * Carga la planilla con la información que esté disponible en los equipos.
     * Se pierde la información que ya esté cargada. Debería precargarse por
     * única vez.
     */
    public void precargarPlanilla() {
        jugadoresL.clear();
        jugadoresV.clear();

        Iterator<Jugador> it = local.getListaBuenaFe().iterator();
        while (it.hasNext()) {
            jugadoresL.add(it.next());
        }

        it = visitante.getListaBuenaFe().iterator();
        while (it.hasNext()) {
            jugadoresV.add(it.next());
        }
        precargada = true;
    }

    /**
     * Indica si en algún momento se precargó la planilla. Se debería utilizar
     * para no modificar una planilla ya cargada.
     * 
     * @return verdadero si la planilla ya fue cargada.
     */
    public boolean fuePrecargada() {
        return precargada;
    }
}
