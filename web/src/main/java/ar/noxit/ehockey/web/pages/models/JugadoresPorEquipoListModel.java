package ar.noxit.ehockey.web.pages.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;

public class JugadoresPorEquipoListModel extends JugadoresParaEquipoListModel {

    public JugadoresPorEquipoListModel(IModel<Integer> equipoId, IClubService service) {
        super(equipoId, service);
    }

    @Override
    protected List<Jugador> doLoad() throws NoxitException {
        if (getIdEquipoModel().getObject() != null)
            return super.doLoad();
        else
            return new ArrayList<Jugador>();
    }
}
