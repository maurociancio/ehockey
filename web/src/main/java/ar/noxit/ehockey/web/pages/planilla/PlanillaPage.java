package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IConversionService;
import ar.noxit.ehockey.web.HTMLRenderer;
import ar.noxit.ehockey.web.PdfResource;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PlanillaPage extends AbstractHeaderPage {

    @SpringBean
    private IConversionService conversionService;

    public PlanillaPage(final IModel<Partido> partido) {
        add(new ResourceLink<Void>("pdf_planilla", new PlanillaPDFResource(partido)));

        Integer partidoId = partido.getObject().getId();
        add(new BookmarkablePageLink<Void>("html_planilla", PlanillaPrinterFriendly.class,
                new PageParameters("partido=" + partidoId)));

        add(new PlanillaPanel("panelPlanilla", new PlanillaModel(partido)));
    }

    private final class PlanillaPDFResource extends PdfResource {

        private final IModel<Partido> partido;

        private PlanillaPDFResource(IModel<Partido> partido) {
            this.partido = partido;
        }

        @Override
        public byte[] getData() {
            try {
                HTMLRenderer h = new HTMLRenderer();
                Integer partidoId = partido.getObject().getId();
                String renderPage = h.renderPage(PlanillaPrinterFriendly.class,
                        new PageParameters("partido=" + partidoId));
                return conversionService.convertir(renderPage);
            } catch (NoxitException e) {
                throw new NoxitRuntimeException(e);
            }
        }
    }
}