package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

public abstract class AltaUsuarioPage extends AbstractContentPage{

    public AltaUsuarioPage() {
        super();

        add(new FeedbackPanel("feedback"));
    }
}
