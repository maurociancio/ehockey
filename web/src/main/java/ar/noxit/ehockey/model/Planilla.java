package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Planilla {
    protected int id;
    
    protected Equipo local;
    protected Equipo visitante;

    protected String dTL;
    protected String capitanL;
    protected String pFisicoL;
    protected String medicoL;
    protected String juezDeMesaL;
    protected String arbitroL;

    protected String dTV;
    protected String capitanV;
    protected String pFisicoV;
    protected String medicoV;
    protected String juezDeMesaV;
    protected String arbitroV;

    protected String observaciones;

    protected List<Jugador> jugadoresL = new ArrayList<Jugador>();
    protected List<Jugador> jugadoresV = new ArrayList<Jugador>();

    // TODO definir el atributo fecha
    // TODO definir el resto de los atributos que son: Torneo, Rueda, Partido,
    // Sector(damas/caballeros), categoría, división, zona

    List<Jugador> goleadoresLocal = new ArrayList<Jugador>();
    List<Jugador> goleadoresVisitante = new ArrayList<Jugador>();

    public Planilla(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
        precargarPlanilla();
    }

    protected Planilla() {

    }

    /**
     * Carga la planilla con la información que esté disponible en los equipos.
     * Se pierde la información que ya esté cargada. Debería precargarse por
     * única vez.
     */
    private void precargarPlanilla() {
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
    }

    /**
     * Cierra una planilla para que no pueda modificarse más. Tener en cuenta
     * que la planilla cerrada es la que devuelve.
     * 
     * @return una planilla que es igual a esta pero que no puede ser
     *         modificada.
     */
    public Planilla finalizarPlanilla() {
        return new PlanillaFinal(this);
    }
}
