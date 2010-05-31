package ar.noxit.ehockey.web.pages.equipos;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.web.pages.models.EquipoAdapterModel;
import ar.noxit.ehockey.web.pages.providers.EquiposDataProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EquipoListadoPage extends BaseEquipoPage {

    @SpringBean
    private IEquipoService equipoService;

    public EquipoListadoPage() {
        List<IColumn<Equipo>> columns = new ArrayList<IColumn<Equipo>>();
        columns.add(new PropertyColumn<Equipo>(Model.of("Club"), "club.nombre"));
        columns.add(new PropertyColumn<Equipo>(Model.of("Nombre de Equipo"), "nombre"));
        columns.add(new PropertyColumn<Equipo>(Model.of("División"), "division"));
        columns.add(new PropertyColumn<Equipo>(Model.of("Sector"), "sector.sector"));
        columns.add(new AbstractColumn<Equipo>(Model.of("Acciones")) {

            @Override
            public void populateItem(Item<ICellPopulator<Equipo>> cellItem, String componentId, IModel<Equipo> rowModel) {
                cellItem.add(new AccionesFragment(componentId, "acciones", EquipoListadoPage.this, rowModel));
            }
        });

        add(new DefaultDataTable<Equipo>("listado", columns, new EquiposDataProvider(equipoService), 20));
    }

    private class AccionesFragment extends Fragment {

        public AccionesFragment(String id, String markupId, MarkupContainer markupProvider, IModel<Equipo> model) {
            super(id, markupId, markupProvider);
            add(new Link<Equipo>("modificar", model) {

                @Override
                public void onClick() {
                    setResponsePage(new EquipoModificarPage(new EquipoAdapterModel(getModel())));
                }
            });
            add(new Link<Equipo>("baja", model) {

                @Override
                public void onClick() {
                    setResponsePage(new EquipoBajaPage(getModel()));
                }
            });
        }
    }
}
