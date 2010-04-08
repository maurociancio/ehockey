package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.List;

public class Torneo {

    private List<Division> divisiones = new ArrayList<Division>();

    public Torneo() {
    }

    public int getCantidadDivisiones() {
        return this.divisiones.size();
    }

    public void agregarDivision(Division division) {
        this.divisiones.add(division);
    }

    public boolean existeDivision(Division division) {
        return this.divisiones.contains(division);
    }

    public Division getDivision(String division) {
        Division div = null;
        for (Division each : divisiones) {
            if (each.getNombre().equals(division)) {
                div = each;
                break;
            }
        }
        return div;
    }

}
