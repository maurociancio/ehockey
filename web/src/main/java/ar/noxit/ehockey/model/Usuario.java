package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ErrorDeLoginException;
import ar.noxit.hasher.Hasher;
import ar.noxit.hasher.MD5Hasher;

public abstract class Usuario {

    private String user; // id del usuario
    private String password;
    private String nombre;
    private String apellido;

    // estos atributos no se persisten
    private boolean logueado = false;
    private Hasher hasher = new MD5Hasher();

    public Usuario(String user, String password) {
        this.user = user;
        this.password = hasher.hash(password);
    }

    protected Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Logueo del usuario después de verificar la contraseña
     * 
     * @param password
     *            constraseña con la que se quiere loguear (sin encriptar)
     * @throws ErrorDeLoginException
     *             cuando no se puede loguear por error de contraseña
     */
    public void loguearse(String password) throws ErrorDeLoginException {
        if (!this.password.equals(hasher.hash(password))) {
            throw new ErrorDeLoginException("Password incorrecto");
        }
        this.logueado = true;
    }

    public void desloguearse() {
        this.logueado = false;
    }

    public boolean estaLogueado() {
        return logueado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            Usuario otro = (Usuario) obj;
            return (this.user.equals(otro.user) && this.password.equals(otro.password));
        } else {
            return false;
        }
    }
}
