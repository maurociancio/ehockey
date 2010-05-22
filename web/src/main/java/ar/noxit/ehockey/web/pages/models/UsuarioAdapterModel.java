package ar.noxit.ehockey.web.pages.models;

import org.apache.commons.lang.NotImplementedException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.usuarios.TipoUsuario;
import ar.noxit.ehockey.web.pages.usuarios.UsuarioDTO;
import ar.noxit.web.wicket.model.AdapterModel;

public class UsuarioAdapterModel extends AdapterModel<UsuarioDTO, Usuario> {
    IClubService clubService;
    UsuarioDTO usuario;

    public UsuarioAdapterModel(IModel<Usuario> delegate, IClubService clubService) {
        super(delegate);
        this.clubService = clubService;
    }

    @Override
    protected UsuarioDTO getObject(IModel<Usuario> original) {
        //la primera vez lo crea
        if (usuario == null) {
            usuario = new UsuarioDTO(new TipoUsuario(original.getObject().getClass()));
            usuario.setApellido(original.getObject().getApellido());
            usuario.setNombre(original.getObject().getNombre());
            usuario.setUser(original.getObject().getUser());
            if (original.getObject() instanceof Representante) {
                Representante rep = (Representante) original.getObject();
                usuario.setClubModel(new IdClubModel(new Model<Integer>(rep.getClub().getId()), clubService));
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
