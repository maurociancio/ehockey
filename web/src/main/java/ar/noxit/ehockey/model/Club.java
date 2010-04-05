package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.List;

public class Club {

    private Integer id;
    private String nombre;
    private List<Equipo> listaEquipos = new ArrayList<Equipo>();

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
