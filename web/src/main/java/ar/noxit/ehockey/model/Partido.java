package ar.noxit.ehockey.model;

public class Partido {
    Equipo local;
    Equipo visitante;

    Planilla planillaPrecargada;
    Planilla planillaFinal;

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    /**
     * La primera vez se crea la planilla, luego se devuelve siempre la misma.
     * Esto asegura que la planilla del partido sea la misma en cualquier
     * momento.
     * 
     * @return planilla del partido precargada
     */
    public Planilla getPlanillaPrecargada() {
        if (planillaPrecargada == null) {
            planillaPrecargada = new Planilla(local, visitante);
        }
        if (!planillaPrecargada.fuePrecargada()) {
            planillaPrecargada.precargarPlanilla();
        }
        return planillaPrecargada;
    }
}
