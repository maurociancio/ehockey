package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

public class Equipo {

    private Integer id;
    private String nombre;

    private Division division;
    private Sector sector;
    private Club club;

    private ListaBuenaFe listaBuenaFe;

    /**
     * No debe ser usado por los clientes
     * 
     * @param nombre
     * @param club
     */
    public Equipo(String nombre, Club club, Division division, Sector sector) {
        Validate.notNull(nombre, "nombre no puede ser null");
        Validate.notNull(club, "club no puede ser null");
        Validate.notNull(division, "division no puede ser null");
        Validate.notNull(sector, "sector no puede ser null");

        this.club = club;
        this.nombre = nombre;
        this.division = division;
        this.sector = sector;
    }

    public ListaBuenaFe getListaBuenaFe() {
        if (listaBuenaFe == null) {
            listaBuenaFe = new ListaBuenaFe(this);
        }
        return listaBuenaFe;
    }

    public Division getDivision() {
        return division;
    }

    public Sector getSector() {
        return sector;
    }

    /**
     * No debe ser llamados por los clientes
     */
    public void eliminar() {
        this.club = null;
    }

    public String getNombre() {
        return nombre;
    }

    public Club getClub() {
        return club;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected Equipo() {
    }
}
