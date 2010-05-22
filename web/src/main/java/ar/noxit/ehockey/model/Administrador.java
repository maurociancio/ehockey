package ar.noxit.ehockey.model;

public class Administrador extends Usuario {

    public Administrador(String user, String password) {
        super(user, password);
    }

    protected Administrador() {
        super();
    }

    @Override
    public String[] getRoles() {
        return new String[] { ADMIN };
    }
}
