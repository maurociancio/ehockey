package ar.noxit.ehockey.service.transfer;

import java.io.Serializable;

public class PartidoInfo implements Serializable {

    private Integer equipoLocalId;
    private Integer equipoVisitanteId;
    private Integer numeroFecha;

    public PartidoInfo() {
    }

    public Integer getEquipoLocalId() {
        return equipoLocalId;
    }

    public void setEquipoLocalId(Integer equipoLocalId) {
        this.equipoLocalId = equipoLocalId;
    }

    public Integer getEquipoVisitanteId() {
        return equipoVisitanteId;
    }

    public void setEquipoVisitanteId(Integer equipoVisitanteId) {
        this.equipoVisitanteId = equipoVisitanteId;
    }

    public Integer getNumeroFecha() {
        return numeroFecha;
    }

    public void setNumeroFecha(Integer numeroFecha) {
        this.numeroFecha = numeroFecha;
    }
}
