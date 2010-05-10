package ar.noxit.ehockey.web.pages.planilla;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class PlanillaPage extends AbstractHeaderPage {
    @SpringBean
    IPlanillaService planillaService;

    public PlanillaPage(final IModel<Partido> partido) {
        Integer partidoId = partido.getObject().getId();
        add(new BookmarkablePageLink<Void>("html_planilla", PlanillaPrinterFriendly.class,
                new PageParameters("partido=" + partidoId)));

        add(new PlanillaPanel("panelPlanilla", new PlanillaModel(partido)));

        final Form<Void> formPlanilla = new Form<Void>("planillaForm");
        formPlanilla.add(new Button("editar") {

            @Override
            public void onSubmit() {
                setResponsePage(new ModificarPlanillaPage(partido));
            }
        });

        formPlanilla.add(new Button("finalizar") {

            @Override
            public void onSubmit() {
                try {
                    planillaService.finalizarPlanilla(partido.getObject().getId());
                    formPlanilla.setEnabled(false);
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException("No se pudo finalizar la planilla");
                }
            }
        });

        formPlanilla.setEnabled(!partido.getObject().getPlanilla().isFinalizada());

        add(formPlanilla);
    }
}