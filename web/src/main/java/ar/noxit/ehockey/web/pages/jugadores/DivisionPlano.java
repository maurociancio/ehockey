package ar.noxit.ehockey.web.pages.jugadores;

import java.io.Serializable;

public class DivisionPlano implements Serializable {

    private Integer id;
    private String division;

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
