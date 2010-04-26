package ar.noxit.ehockey.web.pages.providers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.noxit.ehockey.model.DatosTabla;
import ar.noxit.ehockey.service.ITablaPosicionesService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.provider.DataProvider;

public class TablaPosicionesDataProvider extends DataProvider<DatosTabla> {

    private ITablaPosicionesService tablaService;
    private Integer torneoId;
    private Integer divisionId;
    private Integer sectorId;
    private Integer equipoId;

    public TablaPosicionesDataProvider(ITablaPosicionesService tablaService) {
        Validate.notNull(tablaService);
        this.tablaService = tablaService;
    }

    public TablaPosicionesDataProvider(ITablaPosicionesService tablaService,
            Integer torneoId) {
        Validate.notNull(tablaService);
        this.tablaService = tablaService;
        this.torneoId = torneoId;
    }

    public TablaPosicionesDataProvider(ITablaPosicionesService tablaService,
            Integer torneoId, Integer divisionId) {
        this(tablaService, torneoId);
        this.divisionId = divisionId;
    }

    public TablaPosicionesDataProvider(ITablaPosicionesService tablaService,
            Integer torneoId, Integer divisionId, Integer sectorId) {
        this(tablaService, torneoId, divisionId);
        this.sectorId = sectorId;
    }

    public TablaPosicionesDataProvider setTorneoId(Integer torneoId) {
        this.torneoId = torneoId;
        return this;
    }

    public TablaPosicionesDataProvider setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
        return this;
    }

    public TablaPosicionesDataProvider setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
        return this;
    }

    public TablaPosicionesDataProvider setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
        return this;
    }

    @Override
    protected List<DatosTabla> loadList() {
        List<DatosTabla> lista = null;
        try {
            if (torneoId != null) {
                if (sectorId == null || divisionId == null) {
                    lista = tablaService.getAllByTorneo(torneoId);
                } else {
                    lista = tablaService.getAllByTorneoSectorDivision(torneoId,
                            sectorId, divisionId);
                }
            } else {
                lista = new ArrayList<DatosTabla>();
            }
        } catch (NoxitException e) {
            lista = new ArrayList<DatosTabla>();
        }
        return lista;
    }

    @Override
    public IModel<DatosTabla> model(DatosTabla object) {
        return new Model<DatosTabla>(object);
    }
}
