package ar.noxit.ehockey.model;

public class EstadoPlanillaRechazada extends EstadoPlanillaCargada {

    @Override
    public boolean estaRechazada() {
        return true;
    }

    @Override
    public String toString() {
        return "Rechazada (requiere cambios)";
    }
}
