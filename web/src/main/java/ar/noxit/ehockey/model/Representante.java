package ar.noxit.ehockey.model;

public class Representante extends Usuario {

    private Club club;
    private String cargo;

    public Representante(String user, String password, Club club) {
        super(user, password);
        this.club = club;
    }

    protected Representante() {
        super();
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
