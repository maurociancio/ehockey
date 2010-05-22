package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

public class PerfilUsuarioPage extends AbstractContentPage{

    public PerfilUsuarioPage(IModel<UsuarioDTO> usuario) {
        add(new FeedbackPanel("feedback"));
        add(new UsuarioBasePanel("basePanel", usuario));
        add(new InfoRepresentantePanel("representantePanel", usuario));
    }
}
