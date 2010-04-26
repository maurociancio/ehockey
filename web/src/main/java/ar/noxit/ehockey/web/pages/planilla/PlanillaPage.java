package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;

public class PlanillaPage extends AbstractHeaderPage {

    public PlanillaPage(final IModel<Partido> partido) {
        Integer partidoId = partido.getObject().getId();
        add(new BookmarkablePageLink<Void>("html_planilla", PlanillaPrinterFriendly.class,
                new PageParameters("partido=" + partidoId)));

        add(new PlanillaPanel("panelPlanilla", new PlanillaModel(partido)));
    }
}