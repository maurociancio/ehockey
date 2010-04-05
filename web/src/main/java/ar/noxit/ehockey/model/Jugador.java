package ar.noxit.ehockey.model;

public class Jugador {

    private Integer id;
    private String ficha;
    private String apellido;
    private String nombre;

    public Jugador(String ficha, String apellido, String nombre) {
        this.ficha = ficha;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFicha() {
        return ficha;
    }

    public String getNombre() {
        return nombre;
    }

    protected Jugador() {
    }
}
