package ar.noxit.ehockey.web.pages.models;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;

public class UsuarioModel extends IdLDM<Usuario, String> {

    private IUsuarioService usuarioService;

    public UsuarioModel(IModel<String> idModel, IUsuarioService usuarioService) {
        super(idModel);
        Validate.notNull(usuarioService);
        this.usuarioService = usuarioService;
    }

    @Override
    protected Usuario doLoad(String id) throws NoxitException {
        return usuarioService.get(id);
    }

    @Override
    protected String getObjectId(Usuario object) {
        return object.getUser();
    }
}
