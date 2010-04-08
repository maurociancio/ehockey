package ar.noxit.ehockey.model;

public class Equipo {

    private String nombre;

    /**
     * Dummy equipo
     * 
     * @param nombre
     */
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean equals(Object o) {
        Equipo equipo = (Equipo) o;
        return equipo.getNombre() == this.nombre;
    }
}
