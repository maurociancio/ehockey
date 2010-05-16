package ar.noxit.ehockey.web.pages.providers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.noxit.ehockey.model.DatosTabla;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.ITablaPosicionesService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.provider.DataProvider;

public class TablaPosicionesDataProvider extends DataProvider<DatosTabla> {

    private ITablaPosicionesService tablaService;
    private IModel<Torneo> torneoModel;

    public TablaPosicionesDataProvider(ITablaPosicionesService tablaService,
            IModel<Torneo> torneoModel) {
        Validate.notNull(tablaService);
        this.tablaService = tablaService;
        this.torneoModel = torneoModel;
    }

    @Override
    protected List<DatosTabla> loadList() {
        List<DatosTabla> lista = null;
        try {
            Torneo object = this.torneoModel.getObject();
            if (object != null) {
                lista = tablaService.getAllByTorneo(object.getId());
            } else {
                lista = new ArrayList<DatosTabla>();
            }
        } catch (NoxitException e) {
            throw new NoxitRuntimeException(e);
        }
        return lista;
    }

    @Override
    public IModel<DatosTabla> model(DatosTabla object) {
        return new Model<DatosTabla>(object);
    }
}
