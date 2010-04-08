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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipo other = (Equipo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
