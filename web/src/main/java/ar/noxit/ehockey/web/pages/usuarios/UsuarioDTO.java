package ar.noxit.ehockey.web.pages.usuarios;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    private String user;
    private String password;
    private String nombre;
    private String apellido;
    private String cargo;
    private Integer clubId;
    private TipoUsuario tipo;

    public UsuarioDTO(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public TipoUsuario getTipo() {
        return this.tipo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }
}
