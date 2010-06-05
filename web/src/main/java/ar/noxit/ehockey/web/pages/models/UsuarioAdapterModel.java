package ar.noxit.ehockey.web.pages.models;

import org.apache.commons.lang.NotImplementedException;
import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.usuarios.TipoUsuario;
import ar.noxit.ehockey.web.pages.usuarios.UsuarioDTO;
import ar.noxit.web.wicket.model.AdapterModel;

public class UsuarioAdapterModel extends AdapterModel<UsuarioDTO, Usuario> {

    private UsuarioDTO usuario;

    public UsuarioAdapterModel(IModel<Usuario> delegate) {
        super(delegate);
    }

    @Override
    protected UsuarioDTO getObject(IModel<Usuario> original) {
        // la primera vez lo crea
        if (usuario == null) {
            usuario = new UsuarioDTO(new TipoUsuario(original.getObject().getClass()));
            usuario.setApellido(original.getObject().getApellido());
            usuario.setNombre(original.getObject().getNombre());
            usuario.setUser(original.getObject().getUser());
            if (original.getObject() instanceof Representante) {
                Representante rep = (Representante) original.getObject();
                usuario.setClubId(rep.getClub().getId());
                usuario.setCargo(rep.getCargo());
            }
        }
        return usuario;
    }

    @Override
    protected void setObject(UsuarioDTO object, IModel<Usuario> delegate) {
        throw new NotImplementedException("Operación no permitida");
    }
}
