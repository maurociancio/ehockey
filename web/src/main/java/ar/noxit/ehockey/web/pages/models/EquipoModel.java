package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import org.apache.wicket.model.IModel;

public class EquipoModel extends IdLDM<Equipo, Integer> {

    private IEquiposService equipoService;

    public EquipoModel(IModel<Integer> equipoId, IEquiposService equipoService) {
        super(equipoId);
        this.equipoService = equipoService;
    }

    @Override
    protected Equipo doLoad(Integer id) throws NoxitException {
        return equipoService.get(id);
    }

    @Override
    protected Integer getObjectId(Equipo object) {
        return object.getId();
    }
}
