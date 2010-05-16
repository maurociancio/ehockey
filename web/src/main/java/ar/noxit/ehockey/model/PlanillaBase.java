package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.JugadorSinTarjetasException;
import ar.noxit.ehockey.exception.PlanillaNoModificableException;
import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.ehockey.exception.ViolacionReglaNegocioException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.Validate;

public abstract class PlanillaBase {

    private int id;

    private Partido partido;

    protected DatosEquipoPlanilla datosLocal = new DatosEquipoPlanilla();
    protected DatosEquipoPlanilla datosVisitante = new DatosEquipoPlanilla();

    protected String observaciones;

    private boolean finalizada = false;

    // TODO definir el atributo fecha
    // TODO definir el resto de los atributos que son: Torneo, Rueda, Partido,
    // Sector(damas/caballeros), categoría, división, zona

    public PlanillaBase(Partido partido) {
        Validate.notNull(partido, "El partido no puede ser nulo");
        this.partido = partido;
        precargarPlanilla();
    }

    protected PlanillaBase() {
    }

    // VALIDADORES
    protected void validatePlanillaCerrada() throws PlanillaNoModificableException {
        if (this.finalizada)
            throw new PlanillaNoModificableException();
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

        Iterator<Jugador> it = partido.getLocal().getListaBuenaFe().iterator(partido);
        while (it.hasNext()) {
            datosLocal.getJugadores().add(it.next());
        }

        it = partido.getVisitante().getListaBuenaFe().iterator(partido);
        while (it.hasNext()) {
            datosVisitante.getJugadores().add(it.next());
        }
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

    /**
     * Cierra una planilla para que no pueda modificarse más. Tener en cuenta
     * que la planilla cerrada es la que devuelve.
     * 
     * @throws PlanillaYaFinalizadaException
     */
    public PlanillaBase finalizarPlanilla() throws PlanillaYaFinalizadaException {
        if (this.finalizada) {
            throw new PlanillaYaFinalizadaException();
        }
        this.finalizada = true;

        amonestar(datosLocal.getTarjetas());
        amonestar(datosVisitante.getTarjetas());
        return this;
    }

    private void amonestar(Map<Jugador, TarjetasPartido> tarjetasLocal) {
        for (Map.Entry<Jugador, TarjetasPartido> entry : tarjetasLocal.entrySet()) {
            Jugador jugador = entry.getKey();
            TarjetasPartido tarjetasPartido = entry.getValue();
            Equipo equipoJugador = getEquipoDeJugador(jugador);

            jugador.amonestar(partido, equipoJugador, tarjetasPartido);
        }
    }

    public TarjetasPartido getTarjetasDe(Jugador object) throws JugadorSinTarjetasException {
        try {
            return datosLocal.buscarJugador(object);
        } catch (JugadorSinTarjetasException e) {
            return datosVisitante.buscarJugador(object);
            // TODO validar que no juegue en los dos equipos
        }
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

    public Integer getGolesLocal() {
        return this.datosLocal.getGoles();
    }

    public Integer getGolesVisitante() {
        return this.datosVisitante.getGoles();
    }

    public String getObservaciones() {
        return observaciones;
    }

}
