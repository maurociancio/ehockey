package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

public class Division {

    private Integer id;
    private String division;

    public Division(String division) {
        Validate.notNull(division, "division no puede ser null");

        this.division = division;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return this.division;
    }

    public String toString() {
        return division;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected Division() {
    }
}
