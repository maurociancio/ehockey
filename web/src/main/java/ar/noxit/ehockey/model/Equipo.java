package ar.noxit.ehockey.model;

public class Equipo {

    private Club club;
    private ListaBuenaFe listaBuenaFe;
    private String nombre;
    private Integer id;

    public Equipo(String nombre, Club club) {
        this.club = club;
        this.nombre = nombre;
    }

    public ListaBuenaFe getListaBuenaFe() {
        if (listaBuenaFe == null) {
            listaBuenaFe = new ListaBuenaFe(this);
        }
        return listaBuenaFe;
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
