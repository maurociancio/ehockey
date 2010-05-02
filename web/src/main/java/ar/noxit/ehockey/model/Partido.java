package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.EquiposInvalidosException;
import ar.noxit.ehockey.exception.FechaInvalidaException;
import ar.noxit.ehockey.exception.PartidoYaTerminadoException;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;

public class Partido {

    private Integer id;

    private Equipo local;
    private Equipo visitante;

    private Torneo torneo;

    private Planilla planillaPrecargada;
    private Planilla planillaFinal;

    private Integer fechaDelTorneo;
    private LocalDateTime inicio;
    private Integer rueda;

    private boolean jugado;

    private Integer golesLocal;
    private Integer golesVisitante;

    public Partido(Torneo torneo, Equipo local, Equipo visitante,
            Integer fechaDelTorneo, Integer rueda, LocalDateTime inicio, LocalDateTime now)
            throws EquiposInvalidosException, FechaInvalidaException {

        Validate.notNull(local, "No se puede crear un partido sin equipos");
        Validate.notNull(visitante, "No se puede crear un partido sin equipos");
        Validate.notNull(fechaDelTorneo,
                "La fecha del partido no puede ser null");
        Validate.notNull(inicio, "la fecha de inicio no puede ser null");
        Validate.notNull(torneo, "el torneo no puede ser null");
        Validate.notNull(now, "el instante actual no puede ser null");
        Validate.notNull(rueda, "la rueda no puede ser null");

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
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.rueda = rueda;
    }

    private void crearPlanillas() {
        // inicialmente las planillas deben ser iguales. Luego la planilla final
        // se edita.
        planillaPrecargada = new Planilla(local, visitante).finalizarPlanilla();
        planillaFinal = new Planilla(local, visitante);
    }

    /**
     * La primera vez se crea la planilla, luego se devuelve siempre la misma.
     * Esto asegura que la planilla del partido sea la misma en cualquier
     * momento.
     * 
     * @return planilla del partido precargada. No es editable.
     */
    public Planilla getPlanillaPrecargada() {
        if (planillaPrecargada == null) {
            crearPlanillas();
        }
        return planillaPrecargada;
    }

    /**
     * Devuelve la planilla del partido.
     * 
     * @return planilla del partido
     */
    public Planilla getPlanilla() {
        if (planillaFinal == null) {
            crearPlanillas();
        }
        return planillaFinal;
    }

    /**
     * Cierra la planilla final para que ya no se pueda editar.
     * 
     * @throws PartidoYaTerminadoException
     *             si el partido ya esta terminado
     */
    public void finalizarPlanilla() throws PartidoYaTerminadoException {
        validarPartidoNoJugador();

        planillaFinal = planillaFinal.finalizarPlanilla();
        this.jugado = true;
    }

    public void reprogramar(LocalDateTime nuevaFecha, LocalDateTime now)
            throws FechaInvalidaException, PartidoYaTerminadoException {

        Validate.notNull(nuevaFecha, "la nueva fecha no puede ser null");
        Validate.notNull(now, "la fecha actual no puede ser null");

        validarPartidoNoJugador();
        validarFechaInicio(nuevaFecha, now);

        this.inicio = nuevaFecha;
    }

    public void terminarPartido(Integer golesLocal, Integer golesVisitante) {
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    private void validarPartidoNoJugador() throws PartidoYaTerminadoException {
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

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public Integer getId() {
        return id;
    }

    protected Partido() {
    }
}
