package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.ehockey.exception.JugadorYaBajaException;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.JugadoresPorClubListModel;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.components.AjaxHybridSingleAndMultipleChoicePanel;
import ar.noxit.ehockey.web.pages.components.HybridSingleAndMultipleChoicePanel;
import ar.noxit.ehockey.web.pages.models.ClubModel;
import ar.noxit.ehockey.web.pages.models.ClubesListModel;
import ar.noxit.ehockey.web.pages.models.JugadorModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class JugadorBajaPage extends AbstractJugadorPage {

    @SpringBean
    private IJugadorService jugadorService;
    @SpringBean
    private IClubService clubService;
    private IModel<Integer> jugadorId = new Model<Integer>();
    private IModel<Integer> clubid = new Model<Integer>();
    private Component jugadoresDropDown;

    public JugadorBajaPage() {
        super();
        final IModel<Jugador> jugadorModel = new JugadorModel(jugadorId, jugadorService);

        Form<Jugador> form = new Form<Jugador>("borrar_jugador") {

            @Override
            protected void onSubmit() {
                try {
                    jugadorService.remove(jugadorId.getObject());

                    Jugador jugador = jugadorModel.getObject();
                    setResponsePage(new JugadorBajaPage("El jugador "
                            + jugador.getApellido() + ", "
                            + jugador.getNombre()
                            + " se ha dado de baja exitosamente"));
                } catch (JugadorYaBajaException e) {
                    info("El jugador no se ha podido dar de baja debido a que ya estaba dado de baja.");
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        };

        HybridSingleAndMultipleChoicePanel<Club> component =
                new AjaxHybridSingleAndMultipleChoicePanel<Club>("club",
                        new ClubModel(clubid, clubService),
                        new ClubesListModel(clubService), new ClubRenderer()) {

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        target.addComponent(jugadoresDropDown);
                    }
                };

        form.add(component);

        jugadoresDropDown = new DropDownChoice<Jugador>("jugador",
                jugadorModel,
                new JugadoresPorClubListModel(jugadorService, clubid),
                JugadorRenderer.get()) {

            public boolean isEnabled() {
                return clubid.getObject() != null;
            }
        }.setRequired(true).setOutputMarkupId(true);

        form.add(jugadoresDropDown);

        add(form);
        add(new FeedbackPanel("feedback").
                add(new AttributeModifier("class", true, Model.of("feedbacklabel"))));
    }

    private JugadorBajaPage(String string) {
        this();
        info(string);
    }
}
