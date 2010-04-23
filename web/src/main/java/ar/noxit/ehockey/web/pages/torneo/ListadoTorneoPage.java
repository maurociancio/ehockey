package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;
import ar.noxit.web.wicket.provider.SimpleDataProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ListadoTorneoPage extends BaseTorneoPage {

    @SpringBean
    private ITorneoService torneoService;

    public ListadoTorneoPage() {
        List<IColumn<Torneo>> columns = new ArrayList<IColumn<Torneo>>();

        columns.add(new PropertyColumn<Torneo>(Model.of("Nombre de Torneo"), "nombre"));
        columns.add(new PropertyColumn<Torneo>(Model.of("Cantidad de Partidos"), "cantidadPartidos"));
        columns.add(new AbstractColumn<Torneo>(Model.of("Acciones")) {

            @Override
            public void populateItem(Item<ICellPopulator<Torneo>> cellItem, String componentId, IModel<Torneo> rowModel) {
                cellItem.add(new ActionPanel(componentId, rowModel));
            }
        });

        add(new DefaultDataTable<Torneo>("listado", columns, new TorneosDataProvider(), 20));
    }

    private class ActionPanel extends Panel {

        public ActionPanel(String id, final IModel<Torneo> torneo) {
            super(id);

            add(new Link<Void>("ver_partidos") {

                @Override
                public void onClick() {
                    setResponsePage(new VerPartidosPage(torneo));
                }
            });
        }
    }

    private class TorneosDataProvider extends SimpleDataProvider<Torneo> {

        @Override
        protected List<Torneo> doLoadList() throws NoxitException {
            return torneoService.getAll();
        }

        @Override
        public IModel<Torneo> model(Torneo object) {
            final Integer id = object.getId();
            return new LDM<Torneo>() {

                @Override
                protected Torneo doLoad() throws NoxitException {
                    return torneoService.get(id);
                }
            };
        }
    }
}
