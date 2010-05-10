package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;
import org.apache.wicket.model.IModel;

public class JugadorIdModel extends LDM<Jugador> {

    private IJugadorService jugadorService;
    private IModel<Integer> jugadorId;

    public JugadorIdModel(IJugadorService jugadorService, IModel<Integer> jugadorId) {
        this.jugadorService = jugadorService;
        this.jugadorId = jugadorId;
    }

    @Override
    protected Jugador doLoad() throws NoxitException {
        Integer object = jugadorId.getObject();
        if (object == null)
            return null;
        return jugadorService.get(object);
    }
}
