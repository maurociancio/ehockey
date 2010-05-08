package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

public class Partido {

    private Integer id;

    private Equipo local;
    private Equipo visitante;

    private Planilla planillaPrecargada;
    private Planilla planillaFinal;

    private Integer fechaDelTorneo;

    public Partido(Equipo local, Equipo visitante) {
        Validate.notNull(local, "No se puede crear un partido sin equipos");
        Validate.notNull(visitante, "No se puede crear un partido sin equipos");
        this.local = local;
        this.visitante = visitante;
    }

    private void crearPlanillas() {
        // inicialmente las planillas deben ser iguales. Luego la planilla final
        // se edita.
        planillaPrecargada = new Planilla(this).finalizarPlanilla();
        planillaFinal = new Planilla(this);
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
     */
    public void finalizarPlanilla() {
        planillaFinal = planillaFinal.finalizarPlanilla();
    }

    public Equipo getLocal() {
        return local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public Integer getFechaDelTorneo() {
        return fechaDelTorneo;
    }

    public Integer getId() {
        return id;
    }

    protected Partido() {
    }
}
