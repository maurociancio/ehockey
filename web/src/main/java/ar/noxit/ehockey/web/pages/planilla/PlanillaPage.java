package ar.noxit.ehockey.web.pages.planilla;

import org.apache.wicket.model.IModel;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.service.IConversionService;
import ar.noxit.ehockey.web.HTMLRenderer;
import ar.noxit.ehockey.web.PdfResource;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PlanillaPage extends AbstractHeaderPage {

    @SpringBean
    private IConversionService conversionService;

    public PlanillaPage(IModel<? extends Planilla> planillaModel) {
        add(new ResourceLink<Void>("pdf_planilla", new PdfResource() {

            @Override
            public byte[] getData() {
                try {
                    HTMLRenderer h = new HTMLRenderer();
                    String renderPage = h.renderPage(PlanillaPrinterFriendly.class, new PageParameters());
                    return conversionService.convertir(renderPage);
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        }));

        add(new PlanillaPanel(planillaModel));
    }
}