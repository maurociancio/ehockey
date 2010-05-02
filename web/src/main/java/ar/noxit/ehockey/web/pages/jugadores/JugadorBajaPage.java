package ar.noxit.ehockey.web.pages.jugadores;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.BehaviorsUtil;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.models.ClubListModel;
import ar.noxit.ehockey.web.pages.models.IdClubModel;
import ar.noxit.ehockey.web.pages.models.JugadorModelListModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.JugadorModelRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class JugadorBajaPage extends AbstractJugadorPage {

    @SpringBean
    private IJugadorService jugadorService;
    @SpringBean
    private IClubService clubService;
    private IModel<Jugador> jugador;
    private IModel<Integer> clubid;
    Component jugadoresDropDown;

    public JugadorBajaPage() {
        super();
        Form<Jugador> form = new Form<Jugador>("borrar_jugador", jugador) {

            @Override
            protected void onSubmit() {
                try {
                    Jugador modelObject = jugador.getObject();
                    jugadorService.remove(modelObject);
                    setResponsePage(new JugadorBajaPage("El jugador "
                            + modelObject.getApellido() + ", "
                            + modelObject.getNombre()
                            + " se ha dado de baja exitosamente"));
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        };

        form.add(new DropDownChoice<Club>("club", new IdClubModel(
                new PropertyModel<Integer>(this, "clubid"), clubService),
                new ClubListModel(clubService), new ClubRenderer())
                .setRequired(true).add(
                        new AjaxFormComponentUpdatingBehavior("onchange") {
                            @Override
                            protected void onUpdate(AjaxRequestTarget target) {
                                target.addComponent(jugadoresDropDown);
                                target.addComponent(getComponent().getParent());
                                ((DropDownChoice<IModel<Jugador>>) jugadoresDropDown)
                                        .setChoices(new JugadorModelByClubListModel(
                                                jugadorService,
                                                JugadorBajaPage.this.clubid
                                                        .getObject()));
                                jugadoresDropDown.setVisible(true);
                            }
                        }));

        jugadoresDropDown = new DropDownChoice<IModel<Jugador>>("jugador",
                new PropertyModel<IModel<Jugador>>(this, "jugador"),
                new AllJugadorModelListModel(jugadorService),
                new JugadorModelRenderer()).setRequired(true)
                .setOutputMarkupId(true).setVisible(false);

        form.add(jugadoresDropDown);

        this.add(form);
        add(new FeedbackPanel("feedback"));
    }

    private JugadorBajaPage(String string) {
        this();
        info(string);
    }

    public void setClubid(Integer clubid) {
        if (this.clubid == null) {
            this.clubid = new Model<Integer>();
        }
        this.clubid.setObject(clubid);
    }

    public Integer getClubid() {
        return (this.clubid != null) ? this.clubid.getObject() : null;
    }

    private class AllJugadorModelListModel extends JugadorModelListModel {

        public AllJugadorModelListModel(IJugadorService jugadorService) {
            super(jugadorService);
        }

        @Override
        protected List<Jugador> listToLoad() throws NoxitException {
            return this.getService().getAll();
        }
    }

    private class JugadorModelByClubListModel extends JugadorModelListModel {

        private Integer clubid;

        public JugadorModelByClubListModel(IJugadorService jugadorService,
                Integer clubid) {
            super(jugadorService);
            Validate.notNull(clubid, "El id de club no puede ser nulo");
            this.clubid = clubid;
        }

        @Override
        protected List<Jugador> listToLoad() throws NoxitException {
            return this.getService().getAllByClub(clubid);
        }
    }
}
