package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.web.pages.torneo.VerPartidosPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class VerPartidosLink extends Link<Partido> {

    public VerPartidosLink(String id, IModel<Partido> model) {
        super(id, model);
    }

    @Override
    public void onClick() {
        setResponsePage(new VerPartidosPage(new PropertyModel<Torneo>(getModel(), "torneo")));
    }
}
