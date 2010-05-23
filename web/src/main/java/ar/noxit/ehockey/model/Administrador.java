package ar.noxit.ehockey.model;

import ar.noxit.hasher.Hasher;

public class Administrador extends Usuario {

    public Administrador(String user, String password, Hasher hasher) {
        super(user, password, hasher);
    }

    protected Administrador() {
        super();
    }

    @Override
    public String[] getRoles() {
        return new String[] { ADMIN };
    }
}
