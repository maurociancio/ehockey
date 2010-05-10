package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ViolacionReglaNegocioException;

import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.PlanillaNoModificableException;
import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.Validate;

public class Planilla {

    private int id;

    private Partido partido;

    private DatosEquipoPlanilla datosLocal = new DatosEquipoPlanilla();
    private DatosEquipoPlanilla datosVisitante = new DatosEquipoPlanilla();
    private Map<Jugador, TarjetasPartido> tarjetas = new HashMap<Jugador, TarjetasPartido>();

    private String observaciones;

    private boolean finalizada = false;

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

    private void validatePlanillaCerrada() throws PlanillaNoModificableException {
        if (this.finalizada)
            throw new PlanillaNoModificableException();
    }

    private void agregarJugador(Jugador jugador, Set<Jugador> jugadores) throws JugadorYaPerteneceAListaException {
        Validate.notNull(jugador, "jugador no puede ser null");

        if (jugadores.contains(jugador)) {
            throw new JugadorYaPerteneceAListaException("el jugador ya está en la lista de buena fe");
        }
        jugadores.add(jugador);
    }

    private void agregarJugadores(Collection<Jugador> jugadoresNuevos, Set<Jugador> jugadores) {
        jugadores.clear();
        for (Jugador j : jugadoresNuevos) {
            jugadores.add(j);
        }
    }

    public void agregarJugadorLocal(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {
        validatePlanillaCerrada();
        agregarJugador(jugador, this.datosLocal.getJugadores());
    }

    public void agregarJugadorVisitante(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {
        validatePlanillaCerrada();
        agregarJugador(jugador, this.datosVisitante.getJugadores());
    }

    public void setJugadoresLocal(Collection<Jugador> jugadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        agregarJugadores(jugadores, this.datosLocal.getJugadores());
    }

    public void setJugadoresVisitante(Collection<Jugador> jugadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        agregarJugadores(jugadores, this.datosVisitante.getJugadores());
    }

    public void setGolesLocal(Integer goles) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setGoles(goles);
    }

    public void setGolesVisitante(Integer goles) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setGoles(goles);
    }

    public void setArbitroL(String arbitro) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setArbitro(arbitro);
    }

    public void setDtL(String dt) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setdT(dt);
    }

    public void setGoleadoresL(String goleadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setGoleadores(goleadores);
    }

    public void setJuezMesaL(String juezMesa) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setJuezDeMesa(juezMesa);
    }

    public void setMedicoL(String medico) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setMedico(medico);
    }

    public void setPfL(String pf) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setpFisico(pf);
    };

    public void setArbitroV(String arbitro) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setArbitro(arbitro);
    }

    public void setDtV(String dt) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setdT(dt);
    }

    public void setGoleadoresV(String goleadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setGoleadores(goleadores);
    }

    public void setJuezMesaV(String juezMesa) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setJuezDeMesa(juezMesa);
    }

    public void setMedicoV(String medico) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setMedico(medico);
    }

    public void setPfV(String pf) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setpFisico(pf);
    }

    public void setObservaciones(String observaciones) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.observaciones = observaciones;
    }

    /**
     * Solo para uso de hibernate, no debe ser llamado
     */
    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    /**
     * Cierra una planilla para que no pueda modificarse más. Tener en cuenta
     * que la planilla cerrada es la que devuelve.
     * 
     * @throws PlanillaYaFinalizadaException
     */
    public Planilla finalizarPlanilla() throws PlanillaYaFinalizadaException {
        if (this.finalizada) {
            throw new PlanillaYaFinalizadaException();
        }
        this.finalizada = true;

        for (Map.Entry<Jugador, TarjetasPartido> entry : tarjetas.entrySet()) {
            Jugador jugador = entry.getKey();
            TarjetasPartido tarjetasPartido = entry.getValue();
            Equipo equipoJugador = getEquipoDeJugador(jugador);

            jugador.amonestar(partido, equipoJugador, tarjetasPartido);
        }
        return this;
    }

    private Equipo getEquipoDeJugador(Jugador jugador) {
        Validate.notNull(jugador);

        boolean jugoLocal = datosLocal.jugo(jugador);
        boolean jugoVisitante = datosVisitante.jugo(jugador);

        if (jugoLocal && jugoVisitante) {
            throw new ViolacionReglaNegocioException("el jugador jugo en los dos equipos");
        }

        if (jugoLocal) {
            return getLocal();
        }
        if (jugoVisitante) {
            return getVisitante();
        }

        throw new ViolacionReglaNegocioException("el jugador no jugo en ningun lado");
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

    public boolean isFinalizada() {
        return finalizada;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
