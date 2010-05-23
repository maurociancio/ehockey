package ar.noxit.ehockey.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IClubDao;
import ar.noxit.ehockey.dao.IUsuarioDao;
import ar.noxit.ehockey.exception.UsuarioExistenteException;
import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.usuarios.UsuarioDTO;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.exceptions.persistence.PersistenceException;

public class UsuarioService implements IUsuarioService {

    private IUsuarioDao usuarioDao;
    private IClubDao clubDao;

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void add(UsuarioDTO usuario) throws NoxitException {
        validarUsuarioNoExistente(usuario);

        if (usuario.getTipo().equals(Administrador.class)) {
            Administrador nuevo = new Administrador(usuario.getUser(), usuario.getPassword());
            nuevo.setNombre(usuario.getNombre());
            nuevo.setApellido(usuario.getApellido());
            usuarioDao.save(nuevo);
        } else if (usuario.getTipo().equals(Representante.class)) {
            Representante nuevo = new Representante(usuario.getUser(), usuario.getPassword(), clubDao.get(usuario.getClubId()));
            nuevo.setNombre(usuario.getNombre());
            nuevo.setApellido(usuario.getApellido());
            nuevo.setCargo(usuario.getCargo());
            usuarioDao.save(nuevo);
        }
    }

    private void validarUsuarioNoExistente(UsuarioDTO usuario) throws UsuarioExistenteException {
        try {
            Usuario temp = usuarioDao.get(usuario.getUser());
            if (temp != null) throw new UsuarioExistenteException();
        } catch (PersistenceException e) {
            throw new NoxitRuntimeException("Error intentando verificar existencia de usuario");
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

    public void setClubDao(IClubDao clubDao) {
        this.clubDao = clubDao;
    }

    @Override
    @Transactional
    public void remove(String user) throws NoxitException {
        this.usuarioDao.delete(this.usuarioDao.get(user));
    }
}
