package ar.noxit.ehockey.web.pages.providers;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.web.pages.models.EquipoModel;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.provider.DataProvider;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class EquiposDataProvider extends DataProvider<Equipo> {

    private IEquipoService equipoService;

    public EquiposDataProvider(IEquipoService equipoService) {
        Validate.notNull(equipoService);

        this.equipoService = equipoService;
    }

    @Override
    protected List<Equipo> loadList() {
        try {
            return equipoService.getAll();
        } catch (NoxitException e) {
            throw new NoxitRuntimeException(e);
        }
    }

    @Override
    public IModel<Equipo> model(Equipo object) {
        return new EquipoModel(Model.of(object.getId()), equipoService);
    }
}
