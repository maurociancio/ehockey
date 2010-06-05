package ar.noxit.ehockey.model;

import java.util.List;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

public class JugadoresPorClubListModel extends LDM<List<Jugador>> {

    private IJugadorService jugadorService;
    private IModel<Integer> clubId;

    public JugadoresPorClubListModel(IJugadorService jugadorService, IModel<Integer> clubId) {
        super();
        this.jugadorService = jugadorService;
        this.clubId = clubId;
    }

    @Override
    protected List<Jugador> doLoad() throws NoxitException {
        return jugadorService.getAllByClubDivisionSector(clubId.getObject(), null, null);
    }
}
