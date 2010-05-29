package ar.noxit.ehockey.web.pages.clubes;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.IdClubModel;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.provider.DataProvider;

public class ClubVerPanel extends Panel {

    @SpringBean
    private IClubService clubService;

    public ClubVerPanel(String id) {
        super(id);

        List<IColumn<Club>> columnas = new ArrayList<IColumn<Club>>();

        columnas.add(new PropertyColumn<Club>(Model.of("Nombre del Club"), "nombre"));
        columnas.add(new PropertyColumn<Club>(Model.of("Direccion"), "direccion"));
        columnas.add(new PropertyColumn<Club>(Model.of("Ciudad"), "ciudad"));
        columnas.add(new PropertyColumn<Club>(Model.of("Povincia"), "provincia"));

        DefaultDataTable<Club> tabla = new DefaultDataTable<Club>("clubes", columnas,
                new ClubDataProvider(clubService), 10);
        add(tabla);
    }

    private class ClubDataProvider extends DataProvider<Club> {

        private IClubService clubService;

        public ClubDataProvider(IClubService clubService) {
            this.clubService = clubService;
        }

        @Override
        protected List<Club> loadList() {
            try {
                return clubService.getAll();
            } catch (NoxitException e) {
                return new ArrayList<Club>();
            }
        }

        @Override
        public IModel<Club> model(Club object) {
            return new IdClubModel(new Model<Integer>(object.getId()), clubService);
        }
    }

}
