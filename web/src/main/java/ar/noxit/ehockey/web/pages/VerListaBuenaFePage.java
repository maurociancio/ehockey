package ar.noxit.ehockey.web.pages;

import static ar.noxit.utils.Collections.toList;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.model.LDM;
import ar.noxit.web.wicket.provider.DataProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
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

public class VerListaBuenaFePage extends AbstractContentPage {

    @SpringBean
    private IEquiposService equiposService;

    public VerListaBuenaFePage() {
        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("equipos");

        IModel<Equipo> equipo = new EquipoModel(new Model<Integer>());
        form.add(new DropDownChoice<Equipo>("equipos",
                equipo,
                new EquiposListModel(),
                EquipoRenderer.get())
                .setRequired(true));

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
            if (equipoObj == null) {
                return new ArrayList<Jugador>();
            }
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

    private class EquipoModel extends IdLDM<Equipo, Integer> {

        private EquipoModel(IModel<Integer> idModel) {
            super(idModel);
        }

        @Override
        protected Equipo doLoad(Integer id) throws NoxitException {
            return equiposService.get(id);
        }

        @Override
        protected Integer getObjectId(Equipo equipo) {
            return equipo.getId();
        }
    }

    public class EquiposListModel extends LDM<List<Equipo>> {

        @Override
        protected List<Equipo> doLoad() throws NoxitException {
            return equiposService.getAll();
        }
    }
}
