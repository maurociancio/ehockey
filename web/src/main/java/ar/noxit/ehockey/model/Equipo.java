package ar.noxit.ehockey.model;

public class Equipo {

    private Club club;
    private ListaBuenaFe listaBuenaFe;

    public Equipo(Club club) {
        this.club = club;
    }

    public ListaBuenaFe getListaBuenaFe() {
        if (listaBuenaFe == null) {
            listaBuenaFe = new ListaBuenaFe(this);
        }
        return listaBuenaFe;
    }

    public Club getClub() {
        return club;
    }
}
