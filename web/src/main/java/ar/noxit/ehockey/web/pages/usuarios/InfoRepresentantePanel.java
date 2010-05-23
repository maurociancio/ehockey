package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.IdClubModel;

public class InfoRepresentantePanel extends Panel {

    private IModel<UsuarioDTO> usuario;
    @SpringBean
    IClubService clubService;

    public InfoRepresentantePanel(String id, IModel<UsuarioDTO> usuario) {
        super(id);
        Validate.notNull(usuario, "El usuario no puede ser null");
        this.usuario = usuario;

        IModel<Club> clubModel = new IdClubModel(new PropertyModel<Integer>(usuario, "clubId"), clubService);
        add(new Label("club", new PropertyModel<String>(clubModel, "nombre")));
        add(new Label("cargo", new PropertyModel<String>(usuario, "cargo")));
    }

    @Override
    public boolean isVisible() {
        return usuario.getObject().getTipo().equals(Representante.class);
    }
}
