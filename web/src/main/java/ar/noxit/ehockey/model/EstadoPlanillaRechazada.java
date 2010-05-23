package ar.noxit.ehockey.model;

import org.joda.time.LocalDateTime;

public class EstadoPlanillaRechazada extends EstadoPlanillaCargada {

    @Override
    public boolean estaRechazada() {
        return true;
    }

    @Override
    public String toString() {
        return "Rechazada (requiere cambios)";
    }

    @Override
    public EstadoPlanilla verificarVencimiento(PlanillaVencible vencible, LocalDateTime now) {
        return vencerPlanillaCargada(vencible, now);
    }
}
