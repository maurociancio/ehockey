package ar.noxit.ehockey.service;

import java.util.List;

import org.apache.wicket.Session;

import ar.noxit.ehockey.exception.ErrorDeLoginException;
import ar.noxit.ehockey.exception.SessionClosedException;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.usuarios.UsuarioDTO;
import ar.noxit.exceptions.NoxitException;

public interface IUsuarioService {

    List<Usuario> getAll() throws NoxitException;

    Usuario get(String user) throws NoxitException;

    void update(UsuarioDTO usuario) throws NoxitException;

    void add(UsuarioDTO usuario) throws NoxitException;

    void remove(String user) throws NoxitException;

    public boolean logInUsuario(Usuario usuario, String password) throws ErrorDeLoginException;

    public String getUsuarioConectado(Session session) throws SessionClosedException ;

    public void logOutUser(Session session) throws SessionClosedException ;
}
