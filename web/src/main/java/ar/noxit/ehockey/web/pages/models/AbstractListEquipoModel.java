package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.web.wicket.model.LDM;
import java.util.List;

public abstract class AbstractListEquipoModel extends LDM<List<Equipo>> {

    protected IEquiposService equiposService;

    public AbstractListEquipoModel(IEquiposService equiposService) {
        this.equiposService = equiposService;
    }
}