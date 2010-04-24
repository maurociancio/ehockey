package ar.noxit.ehockey.web.pages.jugadores;

import java.io.Serializable;

public class ClubPlano implements Serializable {

    private Integer id;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
