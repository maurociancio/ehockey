package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.models.JugadorModelListModel;
import ar.noxit.ehockey.web.pages.renderers.JugadorModelRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class JugadorBajaPage extends AbstractJugadorPage {

    @SpringBean
    private IJugadorService jugadorService;
    private IModel<Jugador> jugador;

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
        form.add(new DropDownChoice<IModel<Jugador>>("jugador",
                new PropertyModel<IModel<Jugador>>(this, "jugador"),
                new JugadorModelListModel(jugadorService),
                new JugadorModelRenderer()).setRequired(true));
        this.add(form);
        add(new FeedbackPanel("feedback"));
    }

    private JugadorBajaPage(String string) {
        this();
        info(string);
    }
}
