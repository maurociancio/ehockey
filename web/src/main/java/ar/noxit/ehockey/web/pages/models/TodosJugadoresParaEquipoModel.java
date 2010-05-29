package ar.noxit.ehockey.web.pages.models;

import java.util.List;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

public class TodosJugadoresParaEquipoModel extends LDM<List<Jugador>> {

    private IClubService clubService;
    private IModel<Integer> equipoId;

    public TodosJugadoresParaEquipoModel(IModel<Integer> equipoId, IClubService service) {
        this.clubService = service;
        this.equipoId = equipoId;
    }

    @Override
    protected List<Jugador> doLoad() throws NoxitException {
        return clubService.getJugadoresParaEquipo(equipoId.getObject());
    }
}
