package ar.noxit.ehockey.model;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.Validate;

import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.PlanillaNoModificableException;

public class Planilla {
    protected int id;

    protected Partido partido;

    protected DatosEquipoPlanilla datosLocal = new DatosEquipoPlanilla();
    protected DatosEquipoPlanilla datosVisitante = new DatosEquipoPlanilla();

    protected String observaciones;

    protected boolean finalizada = false;

    // TODO definir el atributo fecha
    // TODO definir el resto de los atributos que son: Torneo, Rueda, Partido,
    // Sector(damas/caballeros), categoría, división, zona

    public Planilla(Partido partido) {
        Validate.notNull(partido, "El partido no puede ser nulo");
        this.partido = partido;
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
        // TODO Hacer que no se carguen los jugadores inhabilitados. Ver si
        // verificar aca o hacer un iterador en lista de buena fe que devuelva
        // los habilitados.
        datosLocal.getJugadores().clear();
        datosVisitante.getJugadores().clear();

        Iterator<Jugador> it = partido.getLocal().getListaBuenaFe().iterator();
        while (it.hasNext()) {
            datosLocal.getJugadores().add(it.next());
        }

        it = partido.getVisitante().getListaBuenaFe().iterator();
        while (it.hasNext()) {
            datosVisitante.getJugadores().add(it.next());
        }
    }

    private void agregarJugador(Jugador jugador, Set<Jugador> jugadores) throws JugadorYaPerteneceAListaException {
        Validate.notNull(jugador, "jugador no puede ser null");

        if (jugadores.contains(jugador)) {
            throw new JugadorYaPerteneceAListaException("el jugador ya está en la lista de buena fe");
        }
        jugadores.add(jugador);
    }

    public void agregarJugadorLocal(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {
        agregarJugador(jugador, this.datosLocal.getJugadores());
    }

    public void agregarJugadorVisitante(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {
        agregarJugador(jugador, this.datosVisitante.getJugadores());
    }

    public void setGolesLocal(Integer goles) throws PlanillaNoModificableException {
        this.datosLocal.setGoles(goles);
    }

    public void setGolesVisitante(Integer goles) throws PlanillaNoModificableException {
        this.datosVisitante.setGoles(goles);
    }

    public void setArbitroL(String arbitro) throws PlanillaNoModificableException {
        this.datosLocal.setArbitro(arbitro);
    }

    public void setDtL(String dt) throws PlanillaNoModificableException {
        this.datosLocal.setdT(dt);
    }

    public void setGoleadoresL(String goleadores) throws PlanillaNoModificableException {
        this.datosLocal.setdT(goleadores);
    }

    public void setJuezMesaL(String juezMesa) throws PlanillaNoModificableException {
        this.datosLocal.setJuezDeMesa(juezMesa);
    }

    public void setMedicoL(String medico) throws PlanillaNoModificableException {
        this.datosLocal.setMedico(medico);
    }

    public void setPfL(String pf) throws PlanillaNoModificableException {
        this.datosLocal.setpFisico(pf);
    };

    public void setArbitroV(String arbitro) throws PlanillaNoModificableException {
        this.datosVisitante.setArbitro(arbitro);
    }

    public void setDtV(String dt) throws PlanillaNoModificableException {
        this.datosVisitante.setdT(dt);
    }

    public void setGoleadoresV(String goleadores) throws PlanillaNoModificableException {
        this.datosVisitante.setdT(goleadores);
    }

    public void setJuezMesaV(String juezMesa) throws PlanillaNoModificableException {
        this.datosVisitante.setJuezDeMesa(juezMesa);
    }

    public void setMedicoV(String medico) throws PlanillaNoModificableException {
        this.datosVisitante.setMedico(medico);
    }

    public void setPfV(String pf) throws PlanillaNoModificableException {
        this.datosVisitante.setpFisico(pf);
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

    public Equipo getLocal() {
        return partido.getLocal();
    }

    public Equipo getVisitante() {
        return partido.getVisitante();
    }

    public Set<Jugador> getJugadoresL() {
        return this.datosLocal.getJugadores();
    }

    public Set<Jugador> getJugadoresV() {
        return this.datosVisitante.getJugadores();
    }

    public Integer getGolesLocal() {
        return this.datosLocal.getGoles();
    }

    public Integer getGolesVisitante() {
        return this.datosVisitante.getGoles();
    }

    public DatosEquipoPlanilla getDatosLocal() {
        return this.datosLocal;
    }

    public DatosEquipoPlanilla getDatosVisitante() {
        return this.datosVisitante;
    }

    public int getId() {
        return id;
    }

}
