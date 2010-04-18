package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import org.apache.wicket.model.IModel;

public class SelectedEquipoModel extends IdLDM<Equipo, Integer> {

    private IEquiposService equiposService;

    public SelectedEquipoModel(IModel<Integer> idModel, IEquiposService equiposService) {
        super(idModel);
        this.equiposService = equiposService;
    }

    @Override
    protected Equipo doLoad(Integer id) throws NoxitException {
        return equiposService.get(id);
    }

    @Override
    protected Integer getObjectId(Equipo equipo) {
        if (equipo == null) {
            return null;
        }
        return equipo.getId();
    }
}