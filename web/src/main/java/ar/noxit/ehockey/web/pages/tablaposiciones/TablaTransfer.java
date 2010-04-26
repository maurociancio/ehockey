package ar.noxit.ehockey.web.pages.tablaposiciones;

import java.io.Serializable;

public class TablaTransfer implements Serializable {

    private Integer TorneoId;
    private Integer divisionId;
    private Integer sectorId;

    public TablaTransfer() {
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public Integer getTorneoId() {
        return TorneoId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public void setTorneoId(Integer torneoId) {
        TorneoId = torneoId;
    }
}
