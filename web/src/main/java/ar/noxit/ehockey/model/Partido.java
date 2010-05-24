package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.EquiposInvalidosException;
import ar.noxit.ehockey.exception.FechaInvalidaException;
import ar.noxit.ehockey.exception.PartidoNoTerminadoException;
import ar.noxit.ehockey.exception.PartidoYaTerminadoException;
import ar.noxit.ehockey.exception.PlanillaNoDisponibleException;
import ar.noxit.ehockey.exception.PlanillaNoFinalizadaException;
import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

public class Partido {

    private Integer id;

    private Equipo local;
    private Equipo visitante;

    private Torneo torneo;

    private PlanillaPrecargada planillaPrecargada;
    private PlanillaFinal planillaFinal;

    private Integer fechaDelTorneo;
    private LocalDateTime inicio;
    private Integer rueda;
    private Integer partido;

    private boolean jugado;

    private static final Duration TIEMPO_PREVIO_PLANILLA = Duration.standardDays(7);

    public Partido(Torneo torneo, Equipo local, Equipo visitante,
            Integer fechaDelTorneo, Integer rueda, Integer partido, LocalDateTime inicio, LocalDateTime now)
            throws EquiposInvalidosException, FechaInvalidaException {

        Validate.notNull(local, "No se puede crear un partido sin equipos");
        Validate.notNull(visitante, "No se puede crear un partido sin equipos");
        Validate.notNull(fechaDelTorneo,
                "La fecha del partido no puede ser null");
        Validate.notNull(inicio, "la fecha de inicio no puede ser null");
        Validate.notNull(torneo, "el torneo no puede ser null");
        Validate.notNull(now, "el instante actual no puede ser null");
        Validate.notNull(rueda, "la rueda no puede ser null");
        Validate.notNull(partido, "el partido no puede ser null");

        if (local.equals(visitante)) {
            throw new EquiposInvalidosException(
                    "equipo local y visitante no pueden ser el mismo");
        }

        validarFechaInicio(inicio, now);

        this.local = local;
        this.visitante = visitante;
        this.fechaDelTorneo = fechaDelTorneo;
        this.inicio = inicio;
        this.torneo = torneo;
        this.jugado = false;
        this.rueda = rueda;
        this.partido = partido;
    }

    /**
     * La primera vez se crea la planilla, luego se devuelve siempre la misma.
     * Esto asegura que la planilla del partido sea la misma en cualquier
     * momento.
     * 
     * @return planilla del partido precargada. No es editable.
     * @throws PlanillaNoFinalizadaException
     */
    public PlanillaPrecargada getPlanillaPrecargada(LocalDateTime now) throws PlanillaNoDisponibleException {
        checkPlanillas(now);

        return planillaPrecargada;
    }

    /**
     * Devuelve la planilla del partido.
     * 
     * @return planilla del partido
     * @throws PlanillaNoFinalizadaException
     */
    public PlanillaFinal getPlanilla(LocalDateTime now) throws PlanillaNoDisponibleException {
        checkPlanillas(now);

        return planillaFinal;
    }

    private void checkPlanillas(LocalDateTime now) throws PlanillaNoDisponibleException {
        verificarTiempoPlanilla(now);
        crearPlanillasSiCorresponde(now);

        planillaFinal.verificarVencimiento(now);
    }

    private void crearPlanillasSiCorresponde(LocalDateTime now) {
        if (planillaPrecargada == null) {
            crearPlanillas(now);
        }
    }

    private void crearPlanillas(LocalDateTime now) {
        planillaPrecargada = new PlanillaPrecargada(this);
        planillaFinal = new PlanillaFinal(planillaPrecargada, now);
    }

    private void verificarTiempoPlanilla(LocalDateTime now) throws PlanillaNoDisponibleException {
        Validate.notNull(now);

        DateTime nowUTC = now.toDateTime(DateTimeZone.UTC);
        DateTime inicioPartidoUTC = inicio.toDateTime(DateTimeZone.UTC);

        // now - inicio partido
        Duration duration = new Duration(nowUTC, inicioPartidoUTC);
        if (duration.isLongerThan(TIEMPO_PREVIO_PLANILLA)) {
            throw new PlanillaNoDisponibleException("la planilla no está disponible");
        }
    }

    /**
     * Cierra la planilla final para que ya no se pueda editar.
     * 
     * @throws PlanillaYaFinalizadaException
     * @throws PartidoNoTerminadoException
     */
    public void validarPlanilla() throws PlanillaYaFinalizadaException, PartidoNoTerminadoException,
            ReglaNegocioException {
        validarPartidoJugado();

        planillaFinal.validar();
    }

    public void publicarPlanilla() throws ReglaNegocioException {
        validarPartidoJugado();

        planillaFinal.publicar();
    }

    public void rechazarPlanilla(String comentario) throws ReglaNegocioException {
        planillaFinal.rechazar(comentario);
    }

    private void validarPartidoJugado() throws PartidoNoTerminadoException {
        if (!this.jugado) {
            throw new PartidoNoTerminadoException("el partido no está terminado");
        }
    }

    public void reprogramar(LocalDateTime nuevaFecha, LocalDateTime now)
            throws FechaInvalidaException, PartidoYaTerminadoException {

        Validate.notNull(nuevaFecha, "la nueva fecha no puede ser null");
        Validate.notNull(now, "la fecha actual no puede ser null");

        validarPartidoNoJugado();
        validarFechaInicio(nuevaFecha, now);

        this.inicio = nuevaFecha;
    }

    public void terminarPartido() throws PartidoYaTerminadoException {
        validarPartidoNoJugado();
        this.jugado = true;
    }

    private void validarPartidoNoJugado() throws PartidoYaTerminadoException {
        if (this.jugado) {
            throw new PartidoYaTerminadoException(
                    "el partido ya está terminado");
        }
    }

    private void validarFechaInicio(LocalDateTime inicio, LocalDateTime now)
            throws FechaInvalidaException {
        if (!inicio.isAfter(now)) {
            throw new FechaInvalidaException(
                    "la fecha de inicio del partido es anterior a la fecha actual");
        }
    }

    private void validarPlanillaCerrada() throws PlanillaNoFinalizadaException {
        if (this.planillaFinal == null) {
            throw new PlanillaNoFinalizadaException();
        } else {
            if (!this.planillaFinal.isFinalizada()) {
                throw new PlanillaNoFinalizadaException();
            }
        }
    }

    public Integer getRueda() {
        return rueda;
    }

    public boolean isJugado() {
        return jugado;
    }

    public Equipo getLocal() {
        return local;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public Integer getFechaDelTorneo() {
        return fechaDelTorneo;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public Integer getGolesLocal() throws PlanillaNoFinalizadaException {
        validarPlanillaCerrada();
        return this.planillaFinal.getGolesLocal();
    }

    public Integer getGolesVisitante() throws PlanillaNoFinalizadaException {
        validarPlanillaCerrada();
        return this.planillaFinal.getGolesVisitante();
    }

    public boolean juega(Equipo equipo) {
        Validate.notNull(equipo);

        return local.equals(equipo) || visitante.equals(equipo);
    }

    public Integer getPartido() {
        return partido;
    }

    public Integer getId() {
        return id;
    }

    public String getEstadoPlanilla(LocalDateTime now) throws PlanillaNoDisponibleException {
        return getPlanilla(now).getEstado();
    }

    protected Partido() {
    }
}
