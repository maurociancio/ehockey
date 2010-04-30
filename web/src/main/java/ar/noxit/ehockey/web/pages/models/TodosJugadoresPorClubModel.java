package ar.noxit.ehockey.web.pages.models;

import java.util.List;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

public class TodosJugadoresPorClubModel extends LDM<List<Jugador>> {

    private IClubService clubService;
    private IModel<Integer> clubId;

    public TodosJugadoresPorClubModel(IModel<Integer> clubId, IClubService service) {
        this.clubService = service;
        this.clubId = clubId;
    }

    @Override
    protected List<Jugador> doLoad() throws NoxitException {
        return clubService.getJugadoresPorClub(clubId.getObject());
    }

}
