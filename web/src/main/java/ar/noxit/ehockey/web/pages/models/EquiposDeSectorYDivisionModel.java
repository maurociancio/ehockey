package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import java.util.List;
import org.apache.wicket.model.IModel;

public class EquiposDeSectorYDivisionModel extends AbstractListEquipoModel {

    private IModel<Integer> sectorId;
    private IModel<Integer> divisionId;

    public EquiposDeSectorYDivisionModel(IEquiposService equiposService, IModel<Integer> sectorId,
            IModel<Integer> divisionId) {
        super(equiposService);

        this.sectorId = sectorId;
        this.divisionId = divisionId;
    }

    @Override
    protected List<Equipo> doLoad() throws NoxitException {
        Integer sector = sectorId.getObject();
        Integer division = divisionId.getObject();

        return this.equiposService.getEquiposDe(sector, division);
    }
}
