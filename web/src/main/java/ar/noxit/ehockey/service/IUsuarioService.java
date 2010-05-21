package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.usuarios.UsuarioDTO;
import ar.noxit.exceptions.NoxitException;

public interface IUsuarioService {

    List<Usuario> getAll() throws NoxitException;
    Usuario get(String user) throws NoxitException;
    void update(UsuarioDTO usuario) throws NoxitException;
    void add(UsuarioDTO usuario) throws NoxitException;
}
