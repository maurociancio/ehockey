package ar.noxit.ehockey.web.pages.jugadores;

import java.io.Serializable;

public class SectorPlano implements Serializable {

    private Integer id;
    private String sector;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
