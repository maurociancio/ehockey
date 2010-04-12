package ar.noxit.ehockey.model;

public class Partido {

    Integer id;
    
    private Equipo local;
    private Equipo visitante;

    private Planilla planillaPrecargada;
    private Planilla planillaFinal;
    
    private Integer fechaDelTorneo;

    protected Partido() {
        
    }
    
    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
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
}
