package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.utils.Collections;
import ar.noxit.web.wicket.column.AbstractReadOnlyColumn;
import ar.noxit.web.wicket.model.LDM;
import ar.noxit.web.wicket.provider.DataProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class VerPartidosPage extends BaseTorneoPage {

    public VerPartidosPage(IModel<Torneo> torneo) {
        Validate.notNull(torneo, "torneo no puede ser null");

        List<IColumn<Partido>> columns = new ArrayList<IColumn<Partido>>();
        columns.add(new PropertyColumn<Partido>(Model.of("Local"), "local.nombre"));
        columns.add(new PropertyColumn<Partido>(Model.of("Visitante"), "visitante.nombre"));
        columns.add(new AbstractReadOnlyColumn<Partido>(Model.of("Jugado?")) {

            @Override
            protected String getLabelString(Partido object) {
                return object.isJugado() ? "Si" : "No";
            }
        });
        columns.add(new AbstractColumn<Partido>(Model.of("Reprogramar")) {

            @Override
            public void populateItem(Item<ICellPopulator<Partido>> cellItem, String componentId,
                    IModel<Partido> rowModel) {
                cellItem.add(new ReprogramarPartidoPanel(componentId, rowModel));
            }
        });

        add(new DefaultDataTable<Partido>("partidos", columns, new PartidosFromTorneoDataProvider(torneo), 20));
    }

    private final class PartidosFromTorneoDataProvider extends DataProvider<Partido> {

        private IModel<Torneo> torneo;

        public PartidosFromTorneoDataProvider(IModel<Torneo> torneo) {
            Validate.notNull(torneo);
            this.torneo = torneo;
        }

        @Override
        protected List<Partido> loadList() {
            Torneo t = torneo.getObject();
            return Collections.toList(t.iteradorPartidos());
        }

        @Override
        public IModel<Partido> model(Partido object) {
            final Integer id = object.getId();
            return new LDM<Partido>(object) {

                @Override
                protected Partido doLoad() throws NoxitException {
                    List<Partido> partidos = loadList();
                    for (Partido p : partidos) {
                        if (p.getId().equals(id)) {
                            return p;
                        }
                    }
                    return null;
                }
            };
        }
    }

    private final class ReprogramarPartidoPanel extends Panel {

        public ReprogramarPartidoPanel(String id, final IModel<Partido> partido) {
            super(id);
            add(new Link<Void>("reprogramar") {

                @Override
                public void onClick() {
                    // #TODO implementar reprogramacion
                }

                @Override
                public boolean isVisible() {
                    return !partido.getObject().isJugado();
                }
            });
            add(new WebMarkupContainer("no_reprogramar") {

                @Override
                public boolean isVisible() {
                    return partido.getObject().isJugado();
                }
            });
        }
    }
}
