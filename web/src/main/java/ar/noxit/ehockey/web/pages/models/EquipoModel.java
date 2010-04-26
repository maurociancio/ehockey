package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

public class EquipoModel extends LDM<Equipo> {

    private IEquiposService equipoService;
    private Integer equipoI;

    public EquipoModel(Integer equipoId, IEquiposService equipoService) {
        this.equipoService = equipoService;
        this.equipoI = equipoId;
    }

    @Override
    protected Equipo doLoad() throws NoxitException {
        return equipoService.get(equipoI);
    }

}
