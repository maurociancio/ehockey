package ar.noxit.ehockey.web.pages;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.model.LDM;
import ar.noxit.web.wicket.provider.DataProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends AbstractContentPage {

    @SpringBean
    private IEquiposService equiposService;

    public HomePage() {
        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("equipos");

        IModel<Equipo> equipo = new EquipoModel(new Model<Integer>());
        form.add(new DropDownChoice<Equipo>("equipos",
                equipo,
                new EquiposListModel(),
                new EquipoRenderer())
                .setRequired(true));

        add(form);

        add(new DataView<Jugador>("jugadores", new JugadoresDataProvider(equipo)) {

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
            List<Jugador> j = new ArrayList<Jugador>();
            Iterator<Jugador> it = equipoObj.getListaBuenaFe().iterator();
            while (it.hasNext()) {
                Jugador next = it.next();
                j.add(next);
            }
            return j;
        }

        @Override
        public IModel<Jugador> model(Jugador object) {
            final Integer id = object.getFicha();
            return new LoadableDetachableModel<Jugador>(object) {

                @Override
                protected Jugador load() {
                    for (Jugador j : loadList()) {
                        if (j.getFicha().equals(id)) {
                            return j;
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

    private class EquipoRenderer implements IChoiceRenderer<Equipo> {

        @Override
        public Object getDisplayValue(Equipo object) {
            return object.getNombre();
        }

        @Override
        public String getIdValue(Equipo object, int index) {
            return object.getId().toString();
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
