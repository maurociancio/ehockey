package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import java.util.List;
import org.apache.wicket.model.IModel;

public class EquiposSeleccionadosModel implements IModel<List<Equipo>> {

    private IEquiposService equiposService;
    private IModel<? extends List<Integer>> idsModel;

    public EquiposSeleccionadosModel(IEquiposService equiposService, IModel<? extends List<Integer>> ids) {
        this.equiposService = equiposService;
        this.idsModel = ids;
    }

    @Override
    public List<Equipo> getObject() {
        try {
            // #TODO pasar los ids
            return equiposService.getAll();
        } catch (NoxitException e) {
            throw new NoxitRuntimeException(e);
        }
    }

    @Override
    public void setObject(List<Equipo> object) {
        List<Integer> ids = idsModel.getObject();
        ids.clear();
        for (Equipo equipo : object) {
            ids.add(equipo.getId());
        }
    }

    @Override
    public void detach() {
    }
}
