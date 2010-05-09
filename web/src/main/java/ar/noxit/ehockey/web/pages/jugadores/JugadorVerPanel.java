package ar.noxit.ehockey.web.pages.jugadores;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.providers.JugadorDataProvider;
import ar.noxit.exceptions.NoxitException;

public class JugadorVerPanel extends Panel {

    private IJugadorService jugadorService;

    public JugadorVerPanel(IJugadorService jugadorService) {
        this(jugadorService, new JugadorDataProvider(jugadorService) {

            @Override
            public List<Jugador> listoToLoad() throws NoxitException {
                return getService().getAll();
            }
        });
    }

    public JugadorVerPanel(IJugadorService jugadorService,
            IDataProvider<Jugador> jugadorProvider) {
        super("jugadorespanel");
        this.jugadorService = jugadorService;
        DataView<Jugador> tabla = new DataView<Jugador>("jugadores",
                jugadorProvider) {

            @Override
            public void populateItem(final Item<Jugador> item) {
                item.add(new Link<AbstractContentPage>("editarjugador") {

                    @Override
                    public void onClick() {
                        setResponsePage(new JugadorModificarPage(
                                new Model<JugadorPlano>(
                                        JugadorVerPanel.this.jugadorService
                                                .aplanar(item.getModelObject()))));
                    }
                }.add(new Label("nombreyapellido", new PropertyModel<String>(
                        item.getModel(), "nombre y apellido") {
                    @Override
                    public String getObject() {
                        Jugador jug = (Jugador) this.getTarget();
                        return jug.getApellido() + ", " + jug.getNombre();
                    }
                })));
                item.add(new Label("tipodocumento", new PropertyModel<String>(
                        item.getModel(), "tipoDocumento")));
                item.add(new Label("documento", new PropertyModel<String>(item
                        .getModel(), "documento")));
                item.add(new Label("fechanac", new PropertyModel<String>(item
                        .getModel(), "fechaNacimiento")));
                item.add(new Label("club", new PropertyModel<String>(item
                        .getModel(), "club")));
                item.add(new Label("division", new PropertyModel<String>(item
                        .getModel(), "division")));
                item.add(new Label("sector", new PropertyModel<String>(item
                        .getModel(), "sector")));
                item.add(new Label("fechaalta", new PropertyModel<String>(item
                        .getModel(), "fechaAlta")));
                item.add(new Label("telefono", new PropertyModel<String>(item
                        .getModel(), "telefono")));
            }
        };
        add(tabla);
    }
}
