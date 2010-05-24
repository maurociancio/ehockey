package ar.noxit.ehockey.web.pages.providers;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.ehockey.web.pages.models.EquipoModel;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.provider.DataProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class EquipoDataProvider extends DataProvider<Equipo> {

    private IEquiposService equipoService;

    public EquipoDataProvider(IEquiposService equipoService) {
        this.equipoService = equipoService;
    }

    @Override
    protected List<Equipo> loadList() {
        try {
            return equipoService.getAll();
        } catch (NoxitException e) {
            return new ArrayList<Equipo>();
        }
    }

    @Override
    public IModel<Equipo> model(Equipo object) {
        return new EquipoModel(Model.of(object.getId()), equipoService);
    }
}
