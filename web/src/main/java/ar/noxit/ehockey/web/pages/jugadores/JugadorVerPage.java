package ar.noxit.ehockey.web.pages.jugadores;

import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class JugadorVerPage extends AbstractContentPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorVerPage() {

        add(new DataView<JugadorPlano>("jugadores", new JugadorDataProvider(
                this.jugadorService)) {
            public void populateItem(final Item<JugadorPlano> item) {
                final JugadorPlano jugador = item.getModelObject();
                item.add(new Link<AbstractContentPage>("editarjugador") {
                    @Override
                    public void onClick() {
                        setResponsePage(new JugadorModificarPage(
                                new Model<JugadorPlano>(jugador)));
                    }
                }.add(new Label("nombreyapellido", new Model<String>(jugador
                        .getApellido()
                        + ", " + jugador.getNombre()))));
                item.add(new Label("tipodocumento", new Model<String>(jugador
                        .getTipoDocumento())));
                item.add(new Label("documento", new Model<String>(jugador
                        .getNumeroDocumento())));
            }
        });
    }

    private class JugadorDataProvider implements IDataProvider<JugadorPlano> {

        private IJugadorService jugadorService;

        public JugadorDataProvider(IJugadorService jugadorService) {
            this.jugadorService = jugadorService;
        }

        @Override
        public Iterator<? extends JugadorPlano> iterator(int first, int count) {
            try {
                return jugadorService.getAllPlano().subList(first,
                        first + count).iterator();

            } catch (NoxitException e) {
                throw new NoxitRuntimeException(e);
            }
        }

        @Override
        public IModel<JugadorPlano> model(JugadorPlano object) {
            return new Model<JugadorPlano>(object);
        }

        @Override
        public int size() {
            try {
                return jugadorService.getAll().size();
            } catch (NoxitException e) {
                throw new NoxitRuntimeException(e);
            }
        }

        @Override
        public void detach() {
        }
    }
}
