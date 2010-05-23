package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.partido.PartidoPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PlanillaPrecargadaPage extends AbstractHeaderPage {

    @SpringBean
    private IDateTimeProvider dateTimeProvider;

    public PlanillaPrecargadaPage(final IModel<Partido> partido) {
        Integer partidoId = partido.getObject().getId();

        add(new BookmarkablePageLink<Void>("html_planilla", PlanillaPrinterFriendly.class,
                new PageParameters(String.format("partido=%s,final=0", partidoId))));

        add(new PlanillaPanel("panelPlanilla", new PlanillaPrecargadaModel(partido, dateTimeProvider)));

        add(new Label("estado_partido", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                Partido object = partido.getObject();
                return object.isJugado() ? "Jugado" : "No jugado";
            }
        }));
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(PartidoPage.class);
    }
}