package ar.noxit.ehockey.web.pages.usuarios;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;

public class PerfilUsuarioPage extends AbstractContentPage {

    public PerfilUsuarioPage(IModel<UsuarioDTO> usuario) {
        add(new FeedbackPanel("feedback"));
        add(new UsuarioBasePanel("basePanel", usuario));
        add(new InfoRepresentantePanel("representantePanel", usuario));
    }
}
