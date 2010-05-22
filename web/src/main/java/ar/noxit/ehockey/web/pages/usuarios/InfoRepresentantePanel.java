package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.Representante;

public class InfoRepresentantePanel extends Panel{

    private IModel<UsuarioDTO> usuario;

    public InfoRepresentantePanel(String id, IModel<UsuarioDTO> usuario) {
        super(id);
        Validate.notNull(usuario, "El usuario no puede ser null");
        this.usuario = usuario;

        add(new Label("club", new PropertyModel<String>(usuario, "club.nombre")));
        add(new Label("cargo", new PropertyModel<String>(usuario, "cargo")));
    }

    @Override
    public boolean isVisible() {
        return usuario.getObject().getTipo().equals(Representante.class);
    }
}
