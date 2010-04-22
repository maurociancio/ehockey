package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.EquiposInvalidosException;
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

    public Partido(Torneo torneo, Equipo local, Equipo visitante, Integer fechaDelTorneo, LocalDateTime inicio)
            throws EquiposInvalidosException {

        Validate.notNull(local, "No se puede crear un partido sin equipos");
        Validate.notNull(visitante, "No se puede crear un partido sin equipos");
        Validate.notNull(fechaDelTorneo, "La fecha del partido no puede ser null");
        Validate.notNull(inicio, "la fecha de inicio no puede ser null");
        Validate.notNull(torneo, "el torneo no puede ser null");

        if (local.equals(visitante)) {
            throw new EquiposInvalidosException("equipo local y visitante no pueden ser el mismo");
        }

        this.local = local;
        this.visitante = visitante;
        this.fechaDelTorneo = fechaDelTorneo;
        this.inicio = inicio;
        this.torneo = torneo;
        this.jugado = false;
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
        if (this.jugado) {
            throw new PartidoYaTerminadoException("el partido ya está terminado");
        }
        planillaFinal = planillaFinal.finalizarPlanilla();
        this.jugado = true;
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

    public Integer getId() {
        return id;
    }

    protected Partido() {
    }
}
