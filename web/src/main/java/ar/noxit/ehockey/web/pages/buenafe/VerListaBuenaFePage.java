package ar.noxit.ehockey.web.pages.buenafe;

import static ar.noxit.utils.Collections.toList;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.web.pages.components.EquipoSelectorPanel;
import ar.noxit.ehockey.web.pages.models.EquipoModel;
import ar.noxit.web.wicket.provider.DataProvider;

public class VerListaBuenaFePage extends AbstractListaBuenaFePage {

    @SpringBean
    private IEquipoService equipoService;

    public VerListaBuenaFePage() {
        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("equipos");

        final IModel<Equipo> equipo = new EquipoModel(new Model<Integer>(), equipoService);
        form.add(new EquipoSelectorPanel("equipo", equipo));

        add(form);

        add(new DataView<Jugador>("jugadores", new JugadoresDataProvider(equipo)) {

            @Override
            protected Item<Jugador> newItem(String id, int index, IModel<Jugador> model) {
                return new OddEvenItem<Jugador>(id, index, model);
            }

            @Override
            protected void populateItem(Item<Jugador> item) {
                final IModel<Jugador> model = item.getModel();

                item.add(new Label("ficha", new PropertyModel<String>(model, "ficha")).setRenderBodyOnly(true));
                item.add(new Label("nya", new AbstractReadOnlyModel<String>() {

                    @Override
                    public String getObject() {
                        return model.getObject().getApellido() + " " + model.getObject().getNombre();
                    }
                }).setRenderBodyOnly(true));
            }

            @Override
            public boolean isVisible() {
                return equipo.getObject() != null;
            }
        });
    }

    private class JugadoresDataProvider extends DataProvider<Jugador> {

        private IModel<Equipo> equipo;

        public JugadoresDataProvider(IModel<Equipo> equipo) {
            this.equipo = equipo;
        }

        @Override
        protected List<Jugador> loadList() {
            Equipo equipoObj = equipo.getObject();
            return toList(equipoObj.getListaBuenaFe().iterator());
        }

        @Override
        public IModel<Jugador> model(Jugador object) {
            final Integer id = object.getFicha();
            return new LoadableDetachableModel<Jugador>(object) {

                @Override
                protected Jugador load() {
                    for (Jugador jugador : loadList()) {
                        if (jugador.getFicha().equals(id)) {
                            return jugador;
                        }
                    }
                    return null;
                }
            };
        }

        @Override
        public void detach() {
            super.detach();
            equipo.detach();
        }
    }
}
