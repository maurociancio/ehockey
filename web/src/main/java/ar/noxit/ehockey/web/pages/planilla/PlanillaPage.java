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
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

@AuthorizeAction(action = "ENABLE", roles = { "USER", "ADMIN" })
public class PlanillaPage extends AbstractHeaderPage {

    @SpringBean
    private IPlanillaService planillaService;
    @SpringBean
    private IExceptionConverter exceptionConverter;
    @SpringBean
    private IDateTimeProvider dateTimeProvider;

    public PlanillaPage(final IModel<Partido> partido) {
        Integer partidoId = partido.getObject().getId();

        add(new FeedbackPanel("feedback"));

        add(new BookmarkablePageLink<Void>("html_planilla",
                PlanillaPrinterFriendly.class, new PageParameters(String
                        .format("partido=%s,final=1", partidoId))));

        final IModel<PlanillaFinal> planillaModel = new PlanillaFinalModel(partido, dateTimeProvider);

        add(new Label("estado", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                return planillaModel.getObject().getEstado();
            }
        }));

        add(new Label("estado_partido", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                Partido object = partido.getObject();
                return object.isJugado() ? "Jugado" : "No jugado";
            }
        }));

        add(new Label("comentario", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                return planillaModel.getObject().getComentario();
            }
        }) {

            @Override
            public boolean isVisible() {
                return planillaModel.getObject().isRechazada();
            }
        });

        add(new PlanillaPanel("panelPlanilla", planillaModel));

        final Form<Void> formPlanilla = new Form<Void>("planillaForm") {

            @Override
            public boolean isEnabled() {
                return !planillaModel.getObject().isFinalizada();
            }
        };
        formPlanilla.add(new Button("editar") {

            @Override
            public void onSubmit() {
                setResponsePage(new ModificarPlanillaPage(partido));
            }

            @Override
            public boolean isVisible() {
                return planillaModel.getObject().isEditable();
            }
        });

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

        formPlanilla.add(new Button("validar") {

            @Override
            public void onSubmit() {
                try {
                    Integer id = partido.getObject().getId();
                    planillaService.validarPlanilla(id);
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }

            @Override
            public boolean isVisible() {
                return planillaModel.getObject().isPublicada();
            }
        });

        formPlanilla.add(new Button("rechazar") {

            @Override
            public void onSubmit() {
                try {
                    // TODO
                    Integer id = partido.getObject().getId();
                    planillaService.rechazarPlanilla(id, "");
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }

            @Override
            public boolean isVisible() {
                return planillaModel.getObject().isPublicada();
            }
        });

        add(formPlanilla);
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(TorneoPage.class);
    }
}