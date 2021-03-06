package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.EquiposInvalidosException;
import ar.noxit.ehockey.exception.FechaInvalidaException;
import ar.noxit.ehockey.exception.FechaPartidoAunNoSucedidaException;
import ar.noxit.ehockey.exception.PartidoNoTerminadoException;
import ar.noxit.ehockey.exception.PartidoYaTerminadoException;
import ar.noxit.ehockey.exception.PlanillaNoDisponibleException;
import ar.noxit.ehockey.exception.PlanillaNoFinalizadaException;
import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTimeConstants;
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

    private static final String DATEFORMAT = "dd/MM/yyyy";

    public Partido(Torneo torneo, Equipo local, Equipo visitante, Integer fechaDelTorneo, Integer rueda,
            Integer partido, LocalDateTime inicio, LocalDateTime now) throws EquiposInvalidosException,
            FechaInvalidaException {

        Validate.notNull(local, "No se puede crear un partido sin equipos");
        Validate.notNull(visitante, "No se puede crear un partido sin equipos");
        Validate.notNull(fechaDelTorneo, "La fecha del partido no puede ser null");
        Validate.notNull(inicio, "la fecha de inicio no puede ser null");
        Validate.notNull(torneo, "el torneo no puede ser null");
        Validate.notNull(now, "el instante actual no puede ser null");
        Validate.notNull(rueda, "la rueda no puede ser null");
        Validate.notNull(partido, "el partido no puede ser null");

        if (local.equals(visitante)) {
            throw new EquiposInvalidosException("equipo local y visitante no pueden ser el mismo");
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
        Validate.notNull(now);

        verificarTiempoPlanillaPrecargada(now);
        crearPlanillaPrecargadaSiNoExiste(now);
        checkVencimientoSiExistePlanillaFinal(now);

        return planillaPrecargada;
    }

    /**
     * Devuelve la planilla del partido.
     * 
     * @return planilla del partido
     * @throws PlanillaNoFinalizadaException
     */
    public PlanillaFinal getPlanilla(LocalDateTime now) throws PlanillaNoDisponibleException {
        Validate.notNull(now);

        try {
            checkPartidoTerminado();
            crearPlanillaFinalSiNoExiste(now);
            checkVencimientoSiExistePlanillaFinal(now);

            return planillaFinal;
        } catch (PartidoNoTerminadoException e) {
            throw new PlanillaNoDisponibleException(e);
        }
    }

    private void crearPlanillaPrecargadaSiNoExiste(LocalDateTime now) {
        if (planillaPrecargada == null) {
            planillaPrecargada = new PlanillaPrecargada(this);
        }
    }

    private void crearPlanillaFinalSiNoExiste(LocalDateTime now) {
        crearPlanillaPrecargadaSiNoExiste(now);
        if (this.planillaFinal == null) {
            this.planillaFinal = new PlanillaFinal(planillaPrecargada, now);
        }
    }

    private void checkPartidoTerminado() throws PartidoNoTerminadoException {
        if (!isJugado()) {
            throw new PartidoNoTerminadoException();
        }
    }

    private void checkExistePlanillaPrecargada() throws PlanillaNoDisponibleException {
        if (planillaPrecargada == null) {
            throw new PlanillaNoDisponibleException(
                    "la planilla precargada no esta creada, no se puede obtener planilla final");
        }
    }

    private void checkVencimientoSiExistePlanillaFinal(LocalDateTime now) {
        if (planillaFinal != null) {
            planillaFinal.verificarVencimiento(now);
        }
    }

    private void verificarTiempoPlanillaPrecargada(LocalDateTime now) throws PlanillaNoDisponibleException {
        Validate.notNull(now);

        LocalDateTime miercolesAnterior = inicio.minusDays(1);
        while (miercolesAnterior.getDayOfWeek() != DateTimeConstants.WEDNESDAY) {
            miercolesAnterior = miercolesAnterior.minusDays(1);
        }
        miercolesAnterior = miercolesAnterior.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                .withMillisOfSecond(0);
        if (now.isBefore(miercolesAnterior)) {
            throw new PlanillaNoDisponibleException("la planilla no está disponible");
        }
    }

    public boolean puedeVersePlanillaPrecargada(LocalDateTime now) {
        try {
            verificarTiempoPlanillaPrecargada(now);
            return true;
        } catch (PlanillaNoDisponibleException e) {
            return false;
        }
    }

    public boolean puedeVersePlanillaFinal(LocalDateTime now) {
        try {
            checkExistePlanillaPrecargada();
            checkPartidoTerminado();
            return true;
        } catch (PlanillaNoDisponibleException e) {
            return false;
        } catch (PartidoNoTerminadoException e) {
            return false;
        }
    }

    public boolean puedeTerminarPartido(LocalDateTime now) {
        Validate.notNull(now);

        return now.isAfter(inicio);
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

    public void finalizarPlanilla() throws ReglaNegocioException {
        planillaFinal.finalizar();
    }

    private void validarPartidoJugado() throws PartidoNoTerminadoException {
        if (!this.jugado) {
            throw new PartidoNoTerminadoException("el partido no está terminado");
        }
    }

    public void reprogramar(LocalDateTime nuevaFecha, LocalDateTime now) throws FechaInvalidaException,
            PartidoYaTerminadoException {

        Validate.notNull(nuevaFecha, "la nueva fecha no puede ser null");
        Validate.notNull(now, "la fecha actual no puede ser null");

        validarPartidoNoJugado();
        validarFechaInicio(nuevaFecha, now);

        this.inicio = nuevaFecha;
    }

    public void terminarPartido(LocalDateTime now) throws PartidoYaTerminadoException,
            FechaPartidoAunNoSucedidaException {

        validarPartidoNoJugado();
        if (!puedeTerminarPartido(now)) {
            throw new FechaPartidoAunNoSucedidaException();
        }

        // TODO aca se puede sancion si no esta creada la planilla.
        crearPlanillaFinalSiNoExiste(now);

        this.jugado = true;
    }

    private void validarPartidoNoJugado() throws PartidoYaTerminadoException {
        if (this.jugado) {
            throw new PartidoYaTerminadoException("el partido ya está terminado");
        }
    }

    private void validarFechaInicio(LocalDateTime inicio, LocalDateTime now) throws FechaInvalidaException {
        if (!inicio.isAfter(now)) {
            throw new FechaInvalidaException("la fecha de inicio del partido es anterior a la fecha actual");
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

    public String getEstadoReducidoPlanilla(LocalDateTime now) throws PlanillaNoDisponibleException {
        return getPlanilla(now).getEstadoReducido();
    }

    @Override
    public String toString() {
        return rueda + " " + fechaDelTorneo + " " + partido + " " + local.getNombre() + " vs " + visitante.getNombre()
                + " dia: " + inicio.toString(DATEFORMAT);
    }

    protected Partido() {
    }
}
