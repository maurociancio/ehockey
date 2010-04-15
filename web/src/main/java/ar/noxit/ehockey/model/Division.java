package ar.noxit.ehockey.model;

public class Division {

    private Integer id;
    private String division;

    public Division(String division) {
        this.division = division;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return this.division;
    }

    protected Division() {
    }
}
