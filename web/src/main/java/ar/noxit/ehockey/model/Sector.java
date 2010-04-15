package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

public class Sector {

    private Integer id;
    private String sector;

    public Sector(String sector) {
        Validate.notNull(sector, "sector no puede ser null");
        this.sector = sector;
    }

    public Integer getId() {
        return id;
    }

    public String getSector() {
        return sector;
    }

    protected Sector() {
    }
}
