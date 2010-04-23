package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.providers.JugadorDataProvider;

public class JugadorVerPage extends AbstractContentPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorVerPage() {

        add(new DataView<Jugador>("jugadores", new JugadorDataProvider(
                jugadorService)) {

            public void populateItem(final Item<Jugador> item) {
                item.add(new Link<AbstractContentPage>("editarjugador") {
                    @Override
                    public void onClick() {
                        setResponsePage(new JugadorModificarPage(
                                new Model<JugadorPlano>(jugadorService
                                        .aplanar(item.getModelObject()))));
                    }
                }.add(new Label("nombreyapellido", new PropertyModel<String>(
                        item.getModel(), "apellido"))));
                item.add(new Label("tipodocumento", new PropertyModel<String>(
                        item.getModel(), "tipoDocumento")));
                item.add(new Label("documento", new PropertyModel<String>(item
                        .getModel(), "documento")));
            }
        });
    }
}