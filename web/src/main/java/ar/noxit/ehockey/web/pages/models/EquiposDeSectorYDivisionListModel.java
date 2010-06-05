package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

import java.util.List;
import org.apache.wicket.model.IModel;

public class EquiposDeSectorYDivisionListModel extends LDM<List<Equipo>> {

    private IModel<Integer> sectorId;
    private IModel<Integer> divisionId;
    private IEquipoService equipoService;

    public EquiposDeSectorYDivisionListModel(IEquipoService equipoService, IModel<Integer> sectorId,
            IModel<Integer> divisionId) {
        super();

        this.equipoService = equipoService;

        this.sectorId = sectorId;
        this.divisionId = divisionId;
    }

    @Override
    protected List<Equipo> doLoad() throws NoxitException {
        Integer sector = sectorId.getObject();
        Integer division = divisionId.getObject();

        return this.equipoService.getEquiposDe(sector, division);
    }
}
