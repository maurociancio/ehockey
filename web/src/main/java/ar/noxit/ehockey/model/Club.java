package ar.noxit.ehockey.model;

public class Club {

    private Integer id;
    private String nombre;

    public Club(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    protected Club() {
    }
}
