package ar.noxit.ehockey.model;

import ar.noxit.hasher.Hasher;

public class Representante extends Usuario {

    private Club club;
    private String cargo;

    public Representante(String user, String password, Hasher hasher, Club club) {
        super(user, password, hasher);
        this.club = club;
    }

    protected Representante() {
        super();
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Club getClub() {
        return club;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String[] getRoles() {
        return Rol.USER;
    }
}
