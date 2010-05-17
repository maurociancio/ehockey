package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PlanillaPage extends AbstractHeaderPage {

    @SpringBean
    private IPlanillaService planillaService;
    @SpringBean
    private IExceptionConverter exceptionConverter;

    public PlanillaPage(final IModel<Partido> partido) {
        Integer partidoId = partido.getObject().getId();

        add(new FeedbackPanel("feedback"));

        add(new BookmarkablePageLink<Void>("html_planilla", PlanillaPrinterFriendly.class,
                new PageParameters(String.format("partido=%s,final=1", partidoId))));

        add(new Label("estado", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                Partido object = partido.getObject();
                return object.getEstadoPlanilla();
            }
        }));

        add(new Label("estado_partido", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                Partido object = partido.getObject();
                return object.isJugado() ? "Jugado" : "No jugado";
            }
        }));

        add(new PlanillaPanel("panelPlanilla", new PlanillaModel(partido)));

        final Form<Void> formPlanilla = new Form<Void>("planillaForm") {

            @Override
            public boolean isEnabled() {
                return !partido.getObject().getPlanilla().isFinalizada();
            }
        };
        formPlanilla.add(new Button("editar") {

            @Override
            public void onSubmit() {
                setResponsePage(new ModificarPlanillaPage(partido));
            }
        });

        formPlanilla.add(new Button("publicar") {

            @Override
            public void onSubmit() {
                try {
                    planillaService.publicarPlanilla(partido.getObject().getId());
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }
        });

        formPlanilla.add(new Button("validar") {

            @Override
            public void onSubmit() {
                try {
                    planillaService.validarPlanilla(partido.getObject().getId());
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }
        });

        formPlanilla.add(new Button("rechazar") {

            @Override
            public void onSubmit() {
                try {
                    // TODO
                    planillaService.rechazarPlanilla(partido.getObject().getId(), "");
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }
        });

        add(formPlanilla);
    }
}