package ar.noxit.ehockey.model;

public class EstadoPlanillaRechazada extends EstadoPlanillaCargada {

    @Override
    public Integer getId() {
        return 2;
    }

    @Override
    public boolean estaRechazada() {
        return true;
    }
}
