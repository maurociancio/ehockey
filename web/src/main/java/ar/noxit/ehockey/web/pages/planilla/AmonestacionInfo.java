package ar.noxit.ehockey.web.pages.planilla;

import java.io.Serializable;

public class AmonestacionInfo implements Serializable {

    private Integer jugadorId;
    private Integer rojas;
    private Integer amarillas;
    private Integer verdes;

    public AmonestacionInfo() {
    }

    public AmonestacionInfo(Integer jugadorId, Integer rojas, Integer amarillas, Integer verdes) {
        this.jugadorId = jugadorId;
        this.rojas = rojas;
        this.amarillas = amarillas;
        this.verdes = verdes;
    }

    public Integer getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Integer jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Integer getRojas() {
        return rojas;
    }

    public void setRojas(Integer rojas) {
        this.rojas = rojas;
    }

    public Integer getAmarillas() {
        return amarillas;
    }

    public void setAmarillas(Integer amarillas) {
        this.amarillas = amarillas;
    }

    public Integer getVerdes() {
        return verdes;
    }

    public void setVerdes(Integer verdes) {
        this.verdes = verdes;
    }
}
