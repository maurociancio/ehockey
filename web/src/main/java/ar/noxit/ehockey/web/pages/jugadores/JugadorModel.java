package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

public class JugadorModel extends LDM<Jugador> {

    private IJugadorService jugadorService;
    private Integer jugadorId;

    public JugadorModel(Integer jugadorId, IJugadorService jugadorService) {
        this.jugadorId = jugadorId;
        this.jugadorService = jugadorService;
    }

    @Override
    protected Jugador doLoad() throws NoxitException {
        return jugadorService.get(jugadorId);
    }
}
