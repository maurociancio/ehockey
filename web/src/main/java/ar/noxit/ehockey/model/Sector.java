package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

public class Sector {

    private String sector;

    public Sector(String sector) {
        Validate.notNull(sector, "sector no puede ser null");
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }
}
