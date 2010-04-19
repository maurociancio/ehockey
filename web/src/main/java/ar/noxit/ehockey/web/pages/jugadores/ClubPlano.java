package ar.noxit.ehockey.web.pages.jugadores;

import java.io.Serializable;

public class ClubPlano implements Serializable {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
