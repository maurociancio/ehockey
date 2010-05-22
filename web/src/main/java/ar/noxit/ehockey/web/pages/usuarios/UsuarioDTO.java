package ar.noxit.ehockey.web.pages.usuarios;

import ar.noxit.ehockey.model.Club;
import java.io.Serializable;
import org.apache.wicket.model.IModel;

public class UsuarioDTO implements Serializable {

    private String user;
    private String password;
    private String nombre;
    private String apellido;
    private String cargo;
    private IModel<Club> club;
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

    public Club getClub() {
        return club.getObject();
    }

    public void setClubModel(IModel<Club> club) {
        this.club = club;
    }

    public void setClub(Club club) {
        this.club.setObject(club);
    }
}
