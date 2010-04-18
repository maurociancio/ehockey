package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;
import java.util.List;

public class TodosEquiposModel extends LDM<List<Equipo>> {

    private IEquiposService equiposService;

    public TodosEquiposModel(IEquiposService equiposService) {
        this.equiposService = equiposService;
    }

    @Override
    protected List<Equipo> doLoad() throws NoxitException {
        return equiposService.getAll();
    }
}