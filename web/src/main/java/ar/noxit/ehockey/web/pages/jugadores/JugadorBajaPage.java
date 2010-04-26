package ar.noxit.ehockey.web.pages.jugadores;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.models.JugadorModel;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class JugadorBajaPage extends AbstractContentPage {

    @SpringBean
    private IJugadorService jugadorService;
    private IModel<Jugador> jugador;

    public JugadorBajaPage() {
        Form<Jugador> form = new Form<Jugador>("borrar_jugador", jugador) {

            @Override
            protected void onSubmit() {
                try {
                    jugadorService.remove(jugador.getObject());
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
                info("El jugador sea ha dado de baja exitosamente");
                // setResponsePage(JugadorPage.class);
            }
        };
        form.add(new DropDownChoice<IModel<Jugador>>("jugador",
                new PropertyModel<IModel<Jugador>>(this, "jugador"),
                new JugadorModelListModel(jugadorService),
                new JugadorModelRenderer()).setRequired(true));
        this.add(form);
        add(new FeedbackPanel("feedback"));
    }

    protected void setJugador(IModel<Jugador> jugador) {
        this.jugador = jugador;
    }

    private class JugadorModelListModel extends
            LoadableDetachableModel<List<IModel<Jugador>>> {

        private IJugadorService jugadorServiceService;

        public JugadorModelListModel(IJugadorService jugadorService) {
            Validate.notNull(jugadorService);

            this.jugadorServiceService = jugadorService;
        }

        @Override
        protected List<IModel<Jugador>> load() {
            List<IModel<Jugador>> lista = new ArrayList<IModel<Jugador>>();
            try {
                List<Jugador> planos = jugadorServiceService.getAll();

                for (Jugador each : planos) {
                    IModel<Jugador> model = new JugadorModel(each.getFicha(),
                            jugadorServiceService);
                    model.setObject(each);
                    lista.add(model);
                }
            } catch (NoxitException ex) {
                // #TODO
                throw new NoxitRuntimeException(ex);
            }
            return lista;
        }
    }

    public class JugadorModelRenderer implements
            IChoiceRenderer<IModel<Jugador>> {
        @Override
        public Object getDisplayValue(IModel<Jugador> arg0) {
            return arg0.getObject().getNombre();
        }

        @Override
        public String getIdValue(IModel<Jugador> arg0, int arg1) {
            return arg0.getObject().getFicha().toString();
        }
    }

    public class JugadorPlanoModelRenderer implements
            IChoiceRenderer<IModel<JugadorPlano>> {
        @Override
        public Object getDisplayValue(IModel<JugadorPlano> arg0) {
            return arg0.getObject().getNombre();
        }

        @Override
        public String getIdValue(IModel<JugadorPlano> arg0, int arg1) {
            return arg0.getObject().getFicha().toString();
        }
    }

}
