package ar.noxit.ehockey.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IUsuarioDao;
import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.usuarios.UsuarioDTO;
import ar.noxit.exceptions.NoxitException;

public class UsuarioService implements IUsuarioService {

    private IUsuarioDao usuarioDao;

    @Override
    @Transactional
    public void add(UsuarioDTO usuario) throws NoxitException {
        if (usuario.getTipo().equals(Administrador.class)) {
            Administrador nuevo = new Administrador(usuario.getUser(), usuario.getPassword());
            nuevo.setNombre(usuario.getNombre());
            nuevo.setApellido(usuario.getApellido());
        } else if (usuario.getTipo().equals(Representante.class)) {
            Representante nuevo = new Representante(usuario.getUser(), usuario.getPassword(), usuario.getClub());
            nuevo.setNombre(usuario.getNombre());
            nuevo.setApellido(usuario.getApellido());
            nuevo.setCargo(usuario.getCargo());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario get(String user) throws NoxitException {
        return usuarioDao.get(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getAll() throws NoxitException {
        return usuarioDao.getAll();
    }

    @Override
    @Transactional
    public void update(UsuarioDTO usuario) throws NoxitException {
        if (usuario.getTipo().equals(Administrador.class)) {
            Administrador original = (Administrador)get(usuario.getUser());
            original.setNombre(usuario.getNombre());
            original.setApellido(usuario.getApellido());
        } else if (usuario.getTipo().equals(Representante.class)) {
            Representante original = (Representante)get(usuario.getUser());
            original.setNombre(usuario.getNombre());
            original.setApellido(usuario.getApellido());
            original.setCargo(usuario.getCargo());
        }
    }

    public void setUsuarioDao(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
}
