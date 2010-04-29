package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public class TodosEquiposModel extends AbstractListEquipoModel {

    public TodosEquiposModel(IEquiposService equiposService) {
        super(equiposService);
    }

    @Override
    protected List<Equipo> doLoad() throws NoxitException {
        return equiposService.getAll();
    }
}