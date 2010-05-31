package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.torneo.TorneoPage;
import ar.noxit.exceptions.NoxitException;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PlanillaPage extends AbstractHeaderPage {

    @SpringBean
    private IPlanillaService planillaService;
    @SpringBean
    private IExceptionConverter exceptionConverter;
    @SpringBean
    private IDateTimeProvider dateTimeProvider;
    private static final List<String> ACCIONES = new ArrayList<String>();
    private static final String ACEPTAR_PLANILLA = "Validar Planilla";
    private static final String RECHAZAR_PLANILLA = "Rechazar Planilla";
    private String accion;
    static {
        ACCIONES.add(ACEPTAR_PLANILLA);
        ACCIONES.add(RECHAZAR_PLANILLA);
    }

    public PlanillaPage(final IModel<Partido> partido) {
        final IModel<PlanillaFinal> planillaModel = new PlanillaFinalModel(partido, dateTimeProvider);
        final IModel<String> comentario = Model.of("");

        Form<Void> form = new Form<Void>("acciones_form") {

            @Override
            public boolean isVisible() {
                return planillaModel.getObject().isPublicada();
            }

            @Override
            protected void onSubmit() {
                try {
                    Integer id = partido.getObject().getId();
                    if (RECHAZAR_PLANILLA.equals(accion)) {
                        planillaService.rechazarPlanilla(id, comentario.getObject());
                        comentario.setObject(null);
                    }
                    if (ACEPTAR_PLANILLA.equals(accion)) {
                        planillaService.validarPlanilla(id);
                    }
                }
                catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }
        };

        final MarkupContainer comentarioContainer = new WebMarkupContainer("comentario");
        Component textArea = new TextArea<String>("comentario", comentario) {

            @Override
            public boolean isVisible() {
                return RECHAZAR_PLANILLA.equals(accion);
            }
        }.setRequired(true);

        comentarioContainer.add(textArea).setOutputMarkupId(true);

        form.add(comentarioContainer);

        form.add(new RadioChoice<String>("acciones",
                new PropertyModel<String>(this, "accion"), ACCIONES).
                setRequired(true).
                add(new AjaxFormChoiceComponentUpdatingBehavior() {

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        target.addComponent(comentarioContainer);
                    }
                }).setOutputMarkupId(true));

        add(form);

        add(new FeedbackPanel("feedback"));
        add(new PlanillaPanel("panelPlanilla", planillaModel));

        add(new Link<Void>("html_planilla") {

            @Override
            public void onClick() {
                setResponsePage(new PlanillaPrinterFriendly(planillaModel));
            }
        });

        add(new Label("estado", new PropertyModel<String>(planillaModel, "estado")));
        add(new Label("estado_partido", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                Partido object = partido.getObject();
                return object.isJugado() ? "Jugado" : "No jugado";
            }
        }));
        add(new Label("comentario", new PropertyModel<String>(planillaModel, "comentario")) {

            @Override
            public boolean isVisible() {
                return planillaModel.getObject().isRechazada();
            }
        });

        final Form<Void> formPlanilla = new Form<Void>("planillaForm") {

            @Override
            public boolean isEnabled() {
                return !planillaModel.getObject().isFinalizada();
            }
        };
        formPlanilla.add(new EditarPlanillaButton("editar", planillaModel, partido));

        formPlanilla.add(new Button("publicar") {

            @Override
            public void onSubmit() {
                try {
                    Integer id = partido.getObject().getId();
                    planillaService.publicarPlanilla(id);
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }

            @Override
            public boolean isVisible() {
                return planillaModel.getObject().isEditable();
            }
        });

        formPlanilla.add(new FinalizarPlanillaButton("finalizar", partido, planillaModel));

        add(formPlanilla);
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(TorneoPage.class);
    }
}