package ar.noxit.ehockey.model;

public class Equipo {

    private Club club;
    private ListaBuenaFe listaBuenaFe;
    private String nombre;

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
}
